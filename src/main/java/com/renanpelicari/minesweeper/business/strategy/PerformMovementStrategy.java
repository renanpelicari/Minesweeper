package com.renanpelicari.minesweeper.business.strategy;

import com.renanpelicari.minesweeper.business.usecase.GenerateBoardPositionMapUseCase;
import com.renanpelicari.minesweeper.business.usecase.GenerateBombPositionsUseCase;
import com.renanpelicari.minesweeper.domain.exception.InvalidMovementException;
import com.renanpelicari.minesweeper.domain.exception.NotFoundException;
import com.renanpelicari.minesweeper.domain.model.BoardPosition;
import com.renanpelicari.minesweeper.domain.model.Coordinate;
import com.renanpelicari.minesweeper.domain.model.Game;
import com.renanpelicari.minesweeper.domain.model.GameStatus;
import com.renanpelicari.minesweeper.infrastructure.config.MinesweeperConfig;
import com.renanpelicari.minesweeper.infrastructure.repository.GameRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Log4j2
public class PerformMovementStrategy {

    private final MinesweeperConfig minesweeperConfig;

    private final GenerateBombPositionsUseCase generateBombPositionsUseCase;

    private final GenerateBoardPositionMapUseCase generateBoardPositionMapUseCase;

    private final GameRepository gameRepository;

    public PerformMovementStrategy(MinesweeperConfig minesweeperConfig,
                                   GenerateBombPositionsUseCase generateBombPositionsUseCase,
                                   GenerateBoardPositionMapUseCase generateBoardPositionMapUseCase,
                                   GameRepository gameRepository) {
        this.minesweeperConfig = minesweeperConfig;
        this.generateBombPositionsUseCase = generateBombPositionsUseCase;
        this.generateBoardPositionMapUseCase = generateBoardPositionMapUseCase;
        this.gameRepository = gameRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Game exec(String gameId, int x, int y) {
        log.info("BEGIN updateGame strategy.");

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException(String.format("Game not found by id=%s", gameId)));

        Game gameToUpdate = performMovement(game, x, y);

        // decrement uncoveredCoordinates based on this move and collect the new status
        int uncoveredCoordinates = (int) gameToUpdate.boardPositionMap().values().stream()
                        .filter(boardPosition -> !boardPosition.alreadyClicked() && !boardPosition.hasBomb())
                        .count();

        GameStatus status = uncoveredCoordinates == 0 ? GameStatus.PLAYER_WON : gameToUpdate.status();

        // save updated game
        Game savedGame = gameRepository.save(game.copyUpdatingStatus(status, uncoveredCoordinates));

        log.info("END updateGame strategy, response={}", savedGame);
        return savedGame;
    }

    private Game performMovement(Game game, int x, int y) {
        // verify the status of game
        validateGameAlreadyFinished(game);

        // new clicked position
        Coordinate coordinate = new Coordinate(x, y);
        Integer coordinateKey = coordinate.hashCode();

        // get the positions in the grid
        Map<Integer, BoardPosition> boardPositionMap = game.boardPositionMap();

        // verify if the position is valid
        validatePositionIsValid(boardPositionMap, coordinate);
        BoardPosition boardPositionClicked = boardPositionMap.get(coordinateKey);

        // verify if the position was clicked before
//        validatePositionAlreadyClicked(boardPositionClicked, coordinate);

        // generate new object from an exists one and update the map
        BoardPosition boardPositionToUpdate = boardPositionClicked.copyUpdatingAlreadyClicked(true);
        boardPositionMap.put(coordinateKey, boardPositionToUpdate);

        // check if position clicked is a bomb
        GameStatus gameStatus = boardPositionClicked.hasBomb() ? GameStatus.PLAYER_LOST : game.status();

        // update and save the game with this current movement
        Game gameToUpdate = game.copyUpdatingMovement(boardPositionMap, gameStatus);

        // If the clicked position has no neighboring bombs, recursively click on neighboring positions
        if (!gameStatus.isGameFinished() && boardPositionClicked.totalNeighbourBombs() == 0) {
            for (Coordinate neighbourCoordinate : boardPositionClicked.neighbourBombs()) {

                Integer neighbourCoordinateKey = neighbourCoordinate.hashCode();

                // Check if the adjacent position is valid, not already clicked and has not a bomb
                if (boardPositionMap.containsKey(neighbourCoordinateKey) &&
                        !boardPositionMap.get(neighbourCoordinateKey).alreadyClicked() &&
                        !boardPositionMap.get(neighbourCoordinateKey).hasBomb() &&
                        !neighbourCoordinate.equals(coordinate)) {
                    // Recursively call the exec() method for the adjacent position
                    performMovement(gameToUpdate, neighbourCoordinate.x(), neighbourCoordinate.y());
                }
            }
        }

        return gameToUpdate;
    }

    private void validateGameAlreadyFinished(Game game) {
        if (game.status().isGameFinished()) {
            String message = String.format("Game %s already finished, current status is %s.", game.id(), game.status());
            log.error("ERROR {}", message);
            throw new InvalidMovementException(message);
        }
    }

    private void validatePositionIsValid(Map<Integer, BoardPosition> boardPositionMap, Coordinate coordinate) {
        if (!boardPositionMap.containsKey(coordinate.hashCode())) {
            String message = String.format("Position {%s} not exists in the game grid.", coordinate);
            log.error("ERROR {}", message);
            throw new InvalidMovementException(message);
        }
    }

    private void validatePositionAlreadyClicked(BoardPosition boardPositionClicked, Coordinate coordinate) {
        if (boardPositionClicked.alreadyClicked()) {
            String message = String.format("Position {%s} was previously clicked.", coordinate);
            log.error("ERROR {}", message);
            throw new InvalidMovementException(message);
        }
    }
}

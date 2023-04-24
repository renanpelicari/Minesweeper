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

        performMovement(game, x, y);

        Game updatedGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException(String.format("Game not found by id=%s", gameId)));

        log.info("END updateGame strategy, response={}", updatedGame);
        return updatedGame;
    }

    private void performMovement(Game game, int x, int y) {
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

        // decrement uncoveredCoordinates based on this move and collect the new status
        int uncoveredCoordinates = game.uncoveredCoordinates() - 1;
        GameStatus gameStatus = getGameStatus(boardPositionClicked.hasBomb(), uncoveredCoordinates, game.totalBombs());

        // update and save the game with this current movement
        Game gameToUpdate = game.copyUpdatingMovement(gameStatus, uncoveredCoordinates, boardPositionMap);
        gameRepository.save(gameToUpdate);

        // If the clicked position has no neighboring bombs, recursively click on neighboring positions
        if (!gameStatus.isGameFinished() && boardPositionClicked.totalNeighbourBombs() == 0) {
            for (Coordinate neighbourCoordinate : boardPositionClicked.neighbourBombs()) {

                Integer neighbourCoordinateKey = neighbourCoordinate.hashCode();

                // Check if the adjacent position is valid and not already clicked
                if (boardPositionMap.containsKey(neighbourCoordinateKey) &&
                        !boardPositionMap.get(neighbourCoordinateKey).alreadyClicked() &&
                        !neighbourCoordinate.equals(coordinate)) {
                    // Recursively call the exec() method for the adjacent position
                    performMovement(game, neighbourCoordinate.x(), neighbourCoordinate.y());
                }
            }
        }
    }

    private GameStatus getGameStatus(boolean explode, int uncoveredCoordinates, int totalBombs) {
        if (explode) {
            return GameStatus.PLAYER_LOST;
        }
        if (uncoveredCoordinates == totalBombs) {
            return GameStatus.PLAYER_WON;
        }
        return GameStatus.IN_PROGRESS;
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

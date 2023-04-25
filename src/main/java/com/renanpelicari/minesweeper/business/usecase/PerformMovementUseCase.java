package com.renanpelicari.minesweeper.business.usecase;

import com.renanpelicari.minesweeper.domain.exception.InvalidMovementException;
import com.renanpelicari.minesweeper.domain.validator.MovementValidator;
import com.renanpelicari.minesweeper.domain.exception.NotFoundException;
import com.renanpelicari.minesweeper.domain.model.BoardPosition;
import com.renanpelicari.minesweeper.domain.model.Coordinate;
import com.renanpelicari.minesweeper.domain.model.Game;
import com.renanpelicari.minesweeper.domain.model.GameStatus;
import com.renanpelicari.minesweeper.infrastructure.repository.GameRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * The use case to handle with movement/clicking.
 */
@Log4j2
@Service
public class PerformMovementUseCase {

    private final GameRepository gameRepository;

    public PerformMovementUseCase(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * Based on gameId and x,y position, this method will perform a movement (a click) in the minesweeper board.
     * @param gameId the game unique identification
     * @param x the coordinate x
     * @param y the coordinate y
     * @return the updated {@link Game} containing the all setup of game with the movements, positions and status.
     * @throws NotFoundException when the game cannot be found by gameId
     * @throws InvalidMovementException when select a position which not exists
     * @throws InvalidMovementException when try to perform a movement in a finished game
     * @throws InvalidMovementException when try to click in a position that has a flag
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Game exec(String gameId, int x, int y) throws NotFoundException, InvalidMovementException {
        log.info("BEGIN updateGame strategy.");

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException(String.format("Game not found by id=%s", gameId)));

        // flag to be used as a control for recursive call
        boolean isTheFirstCall = true;
        Game gameToUpdate = performMovement(game, x, y, isTheFirstCall);

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

    private Game performMovement(Game game, int x, int y, boolean isTheFirstCall) {
        // verify the status of game
        MovementValidator.validateGameAlreadyFinished(game);

        // new clicked position
        Coordinate coordinate = new Coordinate(x, y);
        Integer coordinateKey = coordinate.hashCode();

        // get the positions in the grid
        Map<Integer, BoardPosition> boardPositionMap = game.boardPositionMap();

        // verify if the position is valid
        MovementValidator.validatePositionIsValid(boardPositionMap, coordinate);
        BoardPosition boardPositionClicked = boardPositionMap.get(coordinateKey);

        // verify if the position has flag, but validate only the first time (when user actively click on position)
        if (isTheFirstCall) {
            MovementValidator.validatePositionHasFlag(boardPositionClicked, coordinate);
        }

        // verify if the position was clicked before
        MovementValidator.validatePositionAlreadyClicked(boardPositionClicked, coordinate);

        // generate new object from an exists one and update the map
        BoardPosition boardPositionToUpdate = boardPositionClicked.copyUpdatingAlreadyClicked(true);
        boardPositionMap.put(coordinateKey, boardPositionToUpdate);

        // check if position clicked is a bomb
        GameStatus gameStatus = getGameStatus(game, boardPositionClicked, isTheFirstCall);

        // update and save the game with this current movement
        Game gameToUpdate = game.copyUpdatingMovement(boardPositionMap, gameStatus);

        // If the clicked position has no neighboring bombs, recursively click on neighboring positions
        if (!gameStatus.isGameFinished() && boardPositionClicked.totalNeighbourBombs() == 0) {
            for (Coordinate neighbourCoordinate : boardPositionClicked.neighbourCoordinates()) {

                Integer neighbourCoordinateKey = neighbourCoordinate.hashCode();

                // Check if the adjacent position is valid, not already clicked and has not a bomb
                if (boardPositionMap.containsKey(neighbourCoordinateKey) &&
                        !boardPositionMap.get(neighbourCoordinateKey).alreadyClicked() &&
                        !boardPositionMap.get(neighbourCoordinateKey).hasBomb() &&
                        !neighbourCoordinate.equals(coordinate)) {
                    // Recursively call the exec() method for the adjacent position
                    performMovement(gameToUpdate, neighbourCoordinate.x(), neighbourCoordinate.y(), false);
                }
            }
        }

        return gameToUpdate;
    }

    private static GameStatus getGameStatus(Game game, BoardPosition boardPositionClicked, boolean isTheFirstCall) {
        if (!isTheFirstCall) {
            return game.status();
        }

        if (boardPositionClicked.hasBomb()) {
            return GameStatus.PLAYER_LOST;
        }

        if (game.status() == GameStatus.STARTED) {
            return GameStatus.IN_PROGRESS;
        }

        return game.status();
    }
}

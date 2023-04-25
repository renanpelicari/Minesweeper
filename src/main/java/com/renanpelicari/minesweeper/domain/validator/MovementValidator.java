package com.renanpelicari.minesweeper.domain.validator;

import com.renanpelicari.minesweeper.domain.exception.InvalidMovementException;
import com.renanpelicari.minesweeper.domain.model.BoardPosition;
import com.renanpelicari.minesweeper.domain.model.Coordinate;
import com.renanpelicari.minesweeper.domain.model.Game;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

/**
 * The movement validator, which is used to validate if the click or change flag movement was valid.
 */
@Log4j2
public class MovementValidator {

    /**
     * This method validate if the movement that is being performed is in the game that already finished.
     * @param game {@link Game} the model contain the game information.
     * @throws InvalidMovementException when the game has status finished.
     */
    public static void validateGameAlreadyFinished(Game game) throws InvalidMovementException {
        if (game.status().isGameFinished()) {
            String message = String.format("Game %s already finished, current status is %s.", game.id(), game.status());
            log.error("ERROR {}", message);
            throw new InvalidMovementException(message);
        }
    }

    /**
     * This method validate if the movement that is being performed in a position which not exists.
     * @param boardPositionMap the map containing the hashCode of position as a key, and the {@link BoardPosition} as
     *                         value, to store all the positions in the game.
     * @param coordinate the x,y position in the board.
     * @throws InvalidMovementException when the position is not valid / not exists on the boardPositionMap.
     */
    public static void validatePositionIsValid(Map<Integer, BoardPosition> boardPositionMap, Coordinate coordinate)
            throws InvalidMovementException{
        if (!boardPositionMap.containsKey(coordinate.hashCode())) {
            String message = String.format("Position {%s} not exists in the game grid.", coordinate);
            log.error("ERROR {}", message);
            throw new InvalidMovementException(message);
        }
    }

    /**
     * This method validate if the movement that is being performed in a position which contains a flag.
     * @param boardPositionClicked the {@link BoardPosition} that was clicked.
     * @param coordinate the x,y position in the board.
     * @throws InvalidMovementException when the position has flag.
     */
    public static void validatePositionHasFlag(BoardPosition boardPositionClicked, Coordinate coordinate)
            throws InvalidMovementException {
        if (boardPositionClicked.hasFlag()) {
            String message = String.format("Position {%s} has flag.", coordinate);
            log.warn("WARN {}", message);
            throw new InvalidMovementException(message);
        }
    }

    /**
     * This method validate if the movement that is being performed in a position which was previously clicked.
     * @param boardPositionClicked the {@link BoardPosition} that was clicked.
     * @param coordinate the x,y position in the board.
     * @throws InvalidMovementException when the position was clicked before.
     */
    public static void validatePositionAlreadyClicked(BoardPosition boardPositionClicked, Coordinate coordinate)
            throws InvalidMovementException{
        if (boardPositionClicked.alreadyClicked()) {
            String message = String.format("Position {%s} was previously clicked.", coordinate);
            log.warn("WARN {}", message);
            throw new InvalidMovementException(message);
        }
    }
}


package com.renanpelicari.minesweeper.business.validator;

import com.renanpelicari.minesweeper.domain.exception.InvalidMovementException;
import com.renanpelicari.minesweeper.domain.model.BoardPosition;
import com.renanpelicari.minesweeper.domain.model.Coordinate;
import com.renanpelicari.minesweeper.domain.model.Game;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Log4j2
public class MovementValidator {

    public static void validateGameAlreadyFinished(Game game) {
        if (game.status().isGameFinished()) {
            String message = String.format("Game %s already finished, current status is %s.", game.id(), game.status());
            log.error("ERROR {}", message);
            throw new InvalidMovementException(message);
        }
    }

    public static void validatePositionIsValid(Map<Integer, BoardPosition> boardPositionMap, Coordinate coordinate) {
        if (!boardPositionMap.containsKey(coordinate.hashCode())) {
            String message = String.format("Position {%s} not exists in the game grid.", coordinate);
            log.error("ERROR {}", message);
            throw new InvalidMovementException(message);
        }
    }

    public static void validatePositionHasFlag(BoardPosition boardPositionClicked, Coordinate coordinate) {
        if (boardPositionClicked.hasFlag()) {
            String message = String.format("Position {%s} has flag.", coordinate);
            log.warn("WARN {}", message);
            throw new InvalidMovementException(message);
        }
    }

    public static void validatePositionAlreadyClicked(BoardPosition boardPositionClicked, Coordinate coordinate) {
        if (boardPositionClicked.alreadyClicked()) {
            String message = String.format("Position {%s} was previously clicked.", coordinate);
            log.warn("WARN {}", message);
            throw new InvalidMovementException(message);
        }
    }
}


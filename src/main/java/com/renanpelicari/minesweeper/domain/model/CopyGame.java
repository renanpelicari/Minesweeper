package com.renanpelicari.minesweeper.domain.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * This record has a purpose to keep the initial state of the Game, to be used in case of restart the exists one.
 * @param id the unique id for game.
 * @param status the {@link GameStatus} which represents the status of the game, and if the player won or lost.
 * @param totalBombs the amount of bombs in this game
 * @param uncoveredCoordinates the amount of position that doesn't have bombs and was not clicked yet.
 * @param boardPositionMap the map containing the hashCode of position as a key, and the {@link BoardPosition} as
 *                         value, to store all the positions in the game.
 */
@Builder
@Document(collation = "copy-games")
public record CopyGame(@Id String id,
                       GameStatus status,
                       int totalBombs,
                       int uncoveredCoordinates,
                       Map<Integer, BoardPosition> boardPositionMap) {
}

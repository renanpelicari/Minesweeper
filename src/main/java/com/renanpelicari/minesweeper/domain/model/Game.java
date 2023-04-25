package com.renanpelicari.minesweeper.domain.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * The record of Game, which contains a setup for the game like all the boardPositions, status, and totalBombs.
 * This class follow the immutability principles, and to guarantee this some constructors were added to create a new
 * object based in the current one, modifying specifically some values accordingly with the needs.
 * @param id the unique id for game.
 * @param status the {@link GameStatus} which represents the status of the game, and if the player won or lost.
 * @param totalBombs the amount of bombs in this game
 * @param uncoveredCoordinates the amount of position that doesn't have bombs and was not clicked yet.
 * @param boardPositionMap the map containing the hashCode of position as a key, and the {@link BoardPosition} as
 *                         value, to store all the positions in the game.
 */
@Builder
@Document(collation = "games")
public record Game(@Id String id,
                   GameStatus status,
                   int totalBombs,
                   int uncoveredCoordinates,
                   Map<Integer, BoardPosition> boardPositionMap) {

    /**
     * This construct will copy the current game to a new one, changing only the specific attributes.
     * @param status the {@link GameStatus} which represents the status of the game, and if the player won or lost.
     * @param uncoveredCoordinates
     * @return the updated {@link Game}
     */
    public Game copyUpdatingStatus(GameStatus status, int uncoveredCoordinates) {
        return Game.builder()
                .id(this.id)
                .status(status)
                .totalBombs(this.totalBombs)
                .uncoveredCoordinates(uncoveredCoordinates)
                .boardPositionMap(this.boardPositionMap)
                .build();
    }

    /**
     * This construct will copy the current game to a new one, changing only the specific attributes.
     * @param boardPositionMap the map containing the hashCode of position as a key, and the {@link BoardPosition} as
     *                         value, to store all the positions in the game.
     * @return the updated {@link Game}
     */
    public Game copyUpdatingMovement(Map<Integer, BoardPosition> boardPositionMap) {
        return Game.builder()
                .id(this.id)
                .status(this.status)
                .totalBombs(this.totalBombs)
                .uncoveredCoordinates(this.uncoveredCoordinates)
                .boardPositionMap(boardPositionMap)
                .build();
    }

    /**
     * This construct will copy the current game to a new one, changing only the specific attributes.
     * @param boardPositionMap the map containing the hashCode of position as a key, and the {@link BoardPosition} as
     *                         value, to store all the positions in the game.
     * @param status the {@link GameStatus} which represents the status of the game, and if the player won or lost.
     * @return the updated {@link Game}
     */
    public Game copyUpdatingMovement(Map<Integer, BoardPosition> boardPositionMap, GameStatus status) {
        return Game.builder()
                .id(this.id)
                .status(status)
                .totalBombs(this.totalBombs)
                .uncoveredCoordinates(this.uncoveredCoordinates)
                .boardPositionMap(boardPositionMap)
                .build();
    }
}

package com.renanpelicari.minesweeper.domain.model;

import lombok.Builder;

import java.util.Set;

/**
 * The record of BoardPosition, which contains a setup for that position in the board.
 * This class follow the immutability principles, and to guarantee this some constructors were added to create a new
 * object based in the current one, modifying specifically some values accordingly with the needs.
 * @param coordinate the x,y position in the board.
 * @param hasBomb boolean that indicates if it has a bomb in this position.
 * @param hasFlag boolean that indicates if it has a flag in this position.
 * @param alreadyClicked boolean that indicates if this position was previously clicked.
 * @param totalNeighbourBombs amount of neighbour bombs.
 * @param neighbourCoordinates the set containing the neighbours positions.
 */
@Builder
public record BoardPosition(Coordinate coordinate,
                            boolean hasBomb,
                            boolean hasFlag,
                            boolean alreadyClicked,
                            long totalNeighbourBombs,
                            Set<Coordinate> neighbourCoordinates) {

    /**
     * This construct will copy the current board to a new one, changing only the attribute hasFlag.
     * @param hasFlag boolean that indicates if it has a flag in this position.
     * @return the updated {@link BoardPosition}
     */
    public BoardPosition copyUpdatingFlag(boolean hasFlag) {
        return BoardPosition.builder()
                .coordinate(this.coordinate)
                .hasBomb(this.hasBomb)
                .hasFlag(hasFlag)
                .alreadyClicked(this.alreadyClicked)
                .totalNeighbourBombs(this.totalNeighbourBombs)
                .neighbourCoordinates(this.neighbourCoordinates)
                .build();
    }

    /**
     * This construct will copy the current board to a new one, changing only the attribute alreadyClicked.
     * @param alreadyClicked boolean that indicates if this position was previously clicked.
     * @return the updated {@link BoardPosition}
     */
    public BoardPosition copyUpdatingAlreadyClicked(boolean alreadyClicked) {
        return BoardPosition.builder()
                .coordinate(this.coordinate)
                .hasBomb(this.hasBomb)
                .hasFlag(this.hasFlag)
                .alreadyClicked(alreadyClicked)
                .totalNeighbourBombs(this.totalNeighbourBombs)
                .neighbourCoordinates(this.neighbourCoordinates)
                .build();
    }
}

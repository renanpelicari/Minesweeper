package com.renanpelicari.minesweeper.domain.model;

import lombok.Builder;

import java.util.Set;

@Builder
public record BoardPosition(Coordinate coordinate,
                            boolean hasBomb,
                            boolean hasFlag,
                            boolean alreadyClicked,
                            long totalNeighbourBombs,
                            Set<Coordinate> neighbourCoordinates) {

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

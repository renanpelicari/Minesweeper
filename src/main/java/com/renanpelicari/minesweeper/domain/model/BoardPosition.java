package com.renanpelicari.minesweeper.domain.model;

import lombok.Builder;

import java.util.Set;

@Builder
public record BoardPosition(Coordinate coordinate,
                            boolean hasBomb,
                            boolean hasFlag,
                            boolean alreadyClicked,
                            int totalNeighbourBombs,
                            Set<Coordinate> neighbourBombs) {

    public BoardPosition copyUpdatingAlreadyClicked(boolean alreadyClicked) {
        return BoardPosition.builder()
                .coordinate(this.coordinate)
                .hasBomb(this.hasBomb)
                .hasFlag(this.hasFlag)
                .alreadyClicked(alreadyClicked)
                .totalNeighbourBombs(this.totalNeighbourBombs)
                .neighbourBombs(this.neighbourBombs)
                .build();
    }
}

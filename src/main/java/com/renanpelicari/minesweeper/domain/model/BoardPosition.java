package com.renanpelicari.minesweeper.domain.model;

import lombok.Builder;

import java.util.Set;

@Builder
public record BoardPosition(Coordinate coordinate,
                            boolean hasBomb,
                            boolean alreadyClicked,
                            int totalNeighbourBombs,
                            Set<Coordinate> neighbourBombs) {
}

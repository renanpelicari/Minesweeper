package com.renanpelicari.minesweeper.domain.model;

import java.util.Set;

public record BoardPosition(Coordinate coordinate,
                            boolean hasBomb,
                            boolean alreadyClicked,
                            int totalNeighbourBombs,
                            Set<Coordinate> neighbourBombs) {
}

package com.renanpelicari.minesweeper.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collation = "games")
public record Game(@Id String id,
                   GameStatus status,
                   int totalBombs,
                   int uncoveredCoordinates,
                   Set<BoardPosition> boardPositions) {
}

package com.renanpelicari.minesweeper.domain.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Builder
@Document(collation = "games")
public record Game(@Id String id,
                   GameStatus status,
                   int totalBombs,
                   int uncoveredCoordinates,
                   Set<BoardPosition> boardPositions) {
}

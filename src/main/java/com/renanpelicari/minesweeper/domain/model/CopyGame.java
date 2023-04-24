package com.renanpelicari.minesweeper.domain.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Builder
@Document(collation = "copy-games")
public record CopyGame(@Id String id,
                       GameStatus status,
                       int totalBombs,
                       int uncoveredCoordinates,
                       Map<Integer, BoardPosition> boardPositionMap) {
}

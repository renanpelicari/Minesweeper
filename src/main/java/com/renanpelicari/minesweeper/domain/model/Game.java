package com.renanpelicari.minesweeper.domain.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Builder
@Document(collation = "games")
public record Game(@Id String id,
                   GameStatus status,
                   int totalBombs,
                   int uncoveredCoordinates,
                   Map<Integer, BoardPosition> boardPositionMap) {

    public Game copyUpdatingStatus(GameStatus status, int uncoveredCoordinates) {
        return Game.builder()
                .id(this.id)
                .status(status)
                .totalBombs(this.totalBombs)
                .uncoveredCoordinates(uncoveredCoordinates)
                .boardPositionMap(this.boardPositionMap)
                .build();
    }
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

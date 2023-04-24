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

    public Game copyUpdatingMovement(GameStatus gameStatus,
                                     int uncoveredCoordinates,
                                     Map<Integer, BoardPosition> boardPositionToUpdate) {
        return Game.builder()
                .id(this.id)
                .status(gameStatus)
                .totalBombs(this.totalBombs)
                .uncoveredCoordinates(uncoveredCoordinates)
                .boardPositionMap(boardPositionToUpdate)
                .build();
    }
}

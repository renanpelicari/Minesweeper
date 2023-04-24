package com.renanpelicari.minesweeper.domain.model;

public enum GameStatus {
    STARTED,
    IN_PROGRESS,
    PLAYER_WON,
    PLAYER_LOST;

    public boolean isGameFinished() {
        return this == PLAYER_WON || this == PLAYER_LOST;
    }
}

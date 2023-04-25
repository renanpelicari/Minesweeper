package com.renanpelicari.minesweeper.domain.model;

/**
 * This enum represents the status of the game.
 */
public enum GameStatus {
    STARTED,
    IN_PROGRESS,
    PLAYER_WON,
    PLAYER_LOST;

    /**
     * This method check if the status represents if the game already finished.
     * @return the boolean saying if the game already finished based on the status.
     */
    public boolean isGameFinished() {
        return this == PLAYER_WON || this == PLAYER_LOST;
    }
}

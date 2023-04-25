package com.renanpelicari.minesweeper.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This record reads the configuration of minesweeper from application properties and store in this model.
 * @param width the width of the minesweeper board.
 * @param height the height of the minesweeper board.
 * @param bombs the total bombs of the minesweeper game.
 */
@ConfigurationProperties("minesweeper.config")
public record MinesweeperConfig(
    int width,

    int height,

    int bombs
) {
    /**
     * The constructor that is used to call a validation of this class, to avoid bad configuration at application
     * properties.
     * @param width the width of the minesweeper board.
     * @param height the height of the minesweeper board.
     * @param bombs the total bombs of the minesweeper game.
     */
    public MinesweeperConfig {
        validateBombsCount();
    }

    private void validateBombsCount() {
        if (bombs > width * height) {
            throw new IllegalArgumentException("Number of bombs cannot exceed the grid size.");
        }
    }
}

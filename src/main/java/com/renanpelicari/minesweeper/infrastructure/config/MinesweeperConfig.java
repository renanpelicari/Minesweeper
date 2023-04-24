package com.renanpelicari.minesweeper.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("minesweeper.config")
public record MinesweeperConfig(
    int width,

    int height,

    int bombs
) {
    public MinesweeperConfig {
        validateBombsCount();
    }

    private void validateBombsCount() {
        if (bombs > width * height) {
            throw new IllegalArgumentException("Number of bombs cannot exceed the grid size.");
        }
    }
}

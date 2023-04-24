package com.renanpelicari.minesweeper.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public record MinesweeperConfig(
    @Value("minesweeper.config.width")
    int width,

    @Value("minesweeper.config.height")
    int height,

    @Value("minesweeper.config.bombs")
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

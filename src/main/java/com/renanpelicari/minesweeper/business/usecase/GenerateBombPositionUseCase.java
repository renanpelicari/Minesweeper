package com.renanpelicari.minesweeper.business.usecase;

import com.renanpelicari.minesweeper.domain.model.Coordinate;
import com.renanpelicari.minesweeper.infrastructure.config.MinesweeperConfig;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GenerateBombPositionUseCase {

    private final MinesweeperConfig minesweeperConfig;

    public GenerateBombPositionUseCase(MinesweeperConfig minesweeperConfig) {
        this.minesweeperConfig = minesweeperConfig;
    }


    public Set<Coordinate> exec() {
        int totalBombs = minesweeperConfig.bombs();
        int width = minesweeperConfig.width();
        int height = minesweeperConfig.height();
        int gridPositions = width * height;

        Random random = new Random();

        return random.ints(0, gridPositions)
                .distinct()
                .limit(totalBombs)
                .mapToObj(i -> new Coordinate(i % width, i / width))
                .collect(Collectors.toSet());
    }
}

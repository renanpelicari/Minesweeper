package com.renanpelicari.minesweeper.business.strategy;

import com.renanpelicari.minesweeper.domain.model.Game;
import com.renanpelicari.minesweeper.infrastructure.config.MinesweeperConfig;
import org.springframework.stereotype.Service;

@Service
public class StartNewGameStrategy {

    private MinesweeperConfig minesweeperConfig;


    public StartNewGameStrategy(MinesweeperConfig minesweeperConfig) {
        this.minesweeperConfig = minesweeperConfig;
    }

    public Game exec() {

        return null;

    }

}

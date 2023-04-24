package com.renanpelicari.minesweeper.business.strategy;

import com.renanpelicari.minesweeper.business.usecase.GenerateBombPositionsUseCase;
import com.renanpelicari.minesweeper.domain.model.Game;
import com.renanpelicari.minesweeper.domain.model.GameStatus;
import com.renanpelicari.minesweeper.infrastructure.config.MinesweeperConfig;
import org.springframework.stereotype.Service;

@Service
public class StartNewGameStrategy {

    private final MinesweeperConfig minesweeperConfig;

    private final GenerateBombPositionsUseCase generateBombPositionsUseCase;

    public StartNewGameStrategy(MinesweeperConfig minesweeperConfig,
                                GenerateBombPositionsUseCase generateBombPositionsUseCase) {
        this.minesweeperConfig = minesweeperConfig;
        this.generateBombPositionsUseCase = generateBombPositionsUseCase;
    }


    public Game exec() {

        int uncoveredCoordinates = minesweeperConfig.height() * minesweeperConfig.width();




        Game game = Game.builder()
                .status(GameStatus.STARTED)
                .totalBombs(minesweeperConfig.bombs())
                .uncoveredCoordinates(uncoveredCoordinates)
//                .boardPositions()  // TODO add all positions
                .build();


        return null;

    }

}

package com.renanpelicari.minesweeper.domain.mapper;

import com.renanpelicari.minesweeper.domain.model.CopyGame;
import com.renanpelicari.minesweeper.domain.model.Game;

public class CopyGameMapper {

    public static CopyGame gameToCopy(Game game) {
        return CopyGame.builder()
                .id(game.id())
                .status(game.status())
                .totalBombs(game.totalBombs())
                .uncoveredCoordinates(game.uncoveredCoordinates())
                .boardPositionMap(game.boardPositionMap())
                .build();
    }

    public static Game copyToGame(CopyGame copy) {
        return Game.builder()
                .id(copy.id())
                .status(copy.status())
                .totalBombs(copy.totalBombs())
                .uncoveredCoordinates(copy.uncoveredCoordinates())
                .boardPositionMap(copy.boardPositionMap())
                .build();
    }
}

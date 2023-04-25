package com.renanpelicari.minesweeper.domain.mapper;

import com.renanpelicari.minesweeper.domain.model.CopyGame;
import com.renanpelicari.minesweeper.domain.model.Game;

/**
 * A mapper that convert Game to CopyGame, vise-versa.
 */
public class CopyGameMapper {

    /**
     * Based on game, will convert and return a CopyGame
     * @param game the {@link Game} entity
     * @return a {@link CopyGame} entity
     */
    public static CopyGame gameToCopy(Game game) {
        return CopyGame.builder()
                .id(game.id())
                .status(game.status())
                .totalBombs(game.totalBombs())
                .uncoveredCoordinates(game.uncoveredCoordinates())
                .boardPositionMap(game.boardPositionMap())
                .build();
    }

    /**
     * Based on CopyGame, will convert and return a Game
     * @param copy the {@link CopyGame} entity
     * @return a {@link Game} entity
     */
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

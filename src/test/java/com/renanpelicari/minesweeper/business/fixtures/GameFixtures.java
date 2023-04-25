package com.renanpelicari.minesweeper.business.fixtures;

import com.renanpelicari.minesweeper.domain.model.CopyGame;
import com.renanpelicari.minesweeper.domain.model.Game;
import com.renanpelicari.minesweeper.domain.model.GameStatus;

public class GameFixtures {

    public static final String DEFAULT_ID = "DEFAULT_GAME_ID";

    public static final Game NEW_GAME = Game.builder()
            .status(GameStatus.STARTED)
            .totalBombs(5)
            .uncoveredCoordinates(7)
            .boardPositionMap(BoardPositionMapFixtures.DEFAULT_BOARD_POSITION_MAP)
            .build();

    public static final Game STARTED_GAME = Game.builder()
            .id(DEFAULT_ID)
            .status(GameStatus.STARTED)
            .totalBombs(1)
            .uncoveredCoordinates(3)
            .boardPositionMap(BoardPositionMapFixtures.DEFAULT_BOARD_POSITION_MAP)
            .build();

    public static final Game FINISHED_GAME = STARTED_GAME.copyUpdatingStatus(GameStatus.PLAYER_WON, 0);

    public static final Game STARTED_GAME_FLAG_CHANGED =
            STARTED_GAME.copyUpdatingMovement(BoardPositionMapFixtures.DEFAULT_BOARD_POSITION_MAP_FLAG_CHANGE);

    public static final Game GAME_LOST = STARTED_GAME.copyUpdatingMovement(
            BoardPositionMapFixtures.DEFAULT_BOARD_POSITION_MAP_CLICKING_BOMB,
            GameStatus.PLAYER_LOST
    );

    public static final Game ON_TO_WIN = Game.builder()
            .id(DEFAULT_ID)
            .status(GameStatus.IN_PROGRESS)
            .totalBombs(1)
            .uncoveredCoordinates(1)
            .boardPositionMap(BoardPositionMapFixtures.DEFAULT_BOARD_POSITION_MAP)
            .build();


    public static final Game GAME_WON = Game.builder()
            .id(DEFAULT_ID)
            .status(GameStatus.PLAYER_WON)
            .totalBombs(1)
            .uncoveredCoordinates(0)
            .boardPositionMap(BoardPositionMapFixtures.DEFAULT_BOARD_POSITION_MAP)
            .build();

    public static final CopyGame COPY_GAME = CopyGame.builder()
            .id(DEFAULT_ID)
            .status(GameStatus.STARTED)
            .totalBombs(1)
            .uncoveredCoordinates(3)
            .boardPositionMap(BoardPositionMapFixtures.DEFAULT_BOARD_POSITION_MAP)
            .build();
}

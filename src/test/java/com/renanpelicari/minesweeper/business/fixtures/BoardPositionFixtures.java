package com.renanpelicari.minesweeper.business.fixtures;

import com.renanpelicari.minesweeper.domain.model.BoardPosition;

public class BoardPositionFixtures {

    // alreadyClicked: BOARD_POSITION_00, BOARD_POSITION_10, BOARD_POSITION_20
    // hasFlag: BOARD_POSITION_22
    // hasBomb: BOARD_POSITION_11, BOARD_POSITION_22

    public final static BoardPosition BOARD_POSITION_00 = BoardPosition.builder()
            .coordinate(CoordinateFixtures.COORDINATE_00)
            .hasBomb(false)
            .hasFlag(false)
            .alreadyClicked(true)
            .totalNeighbourBombs(0)
            .neighbourCoordinates(CoordinateFixtures.COORDINATE_00_NEIGHBOURS)
            .build();

    public final static BoardPosition BOARD_POSITION_01 = BoardPosition.builder()
            .coordinate(CoordinateFixtures.COORDINATE_01)
            .hasBomb(false)
            .hasFlag(false)
            .alreadyClicked(false)
            .totalNeighbourBombs(0)
            .neighbourCoordinates(CoordinateFixtures.COORDINATE_01_NEIGHBOURS)
            .build();

    public final static BoardPosition BOARD_POSITION_02 = BoardPosition.builder()
            .coordinate(CoordinateFixtures.COORDINATE_02)
            .hasBomb(false)
            .hasFlag(false)
            .alreadyClicked(false)
            .totalNeighbourBombs(0)
            .neighbourCoordinates(CoordinateFixtures.COORDINATE_02_NEIGHBOURS)
            .build();

    public final static BoardPosition BOARD_POSITION_10 = BoardPosition.builder()
            .coordinate(CoordinateFixtures.COORDINATE_10)
            .hasBomb(false)
            .hasFlag(false)
            .alreadyClicked(true)
            .totalNeighbourBombs(0)
            .neighbourCoordinates(CoordinateFixtures.COORDINATE_10_NEIGHBOURS)
            .build();

    public final static BoardPosition BOARD_POSITION_11 = BoardPosition.builder()
            .coordinate(CoordinateFixtures.COORDINATE_11)
            .hasBomb(true)
            .hasFlag(false)
            .alreadyClicked(false)
            .totalNeighbourBombs(0)
            .neighbourCoordinates(CoordinateFixtures.COORDINATE_11_NEIGHBOURS)
            .build();

    public final static BoardPosition BOARD_POSITION_12 = BoardPosition.builder()
            .coordinate(CoordinateFixtures.COORDINATE_12)
            .hasBomb(false)
            .hasFlag(false)
            .alreadyClicked(false)
            .totalNeighbourBombs(0)
            .neighbourCoordinates(CoordinateFixtures.COORDINATE_12_NEIGHBOURS)
            .build();

    public final static BoardPosition BOARD_POSITION_20 = BoardPosition.builder()
            .coordinate(CoordinateFixtures.COORDINATE_20)
            .hasBomb(false)
            .hasFlag(false)
            .alreadyClicked(true)
            .totalNeighbourBombs(0)
            .neighbourCoordinates(CoordinateFixtures.COORDINATE_20_NEIGHBOURS)
            .build();

    public final static BoardPosition BOARD_POSITION_21 = BoardPosition.builder()
            .coordinate(CoordinateFixtures.COORDINATE_21)
            .hasBomb(false)
            .hasFlag(false)
            .alreadyClicked(false)
            .totalNeighbourBombs(0)
            .neighbourCoordinates(CoordinateFixtures.COORDINATE_21_NEIGHBOURS)
            .build();

    public final static BoardPosition BOARD_POSITION_22 = BoardPosition.builder()
            .coordinate(CoordinateFixtures.COORDINATE_22)
            .hasBomb(true)
            .hasFlag(true)
            .alreadyClicked(false)
            .totalNeighbourBombs(0)
            .neighbourCoordinates(CoordinateFixtures.COORDINATE_22_NEIGHBOURS)
            .build();

    public final static BoardPosition BOARD_POSITION_22_CHANGE_FLAG = BOARD_POSITION_22.copyUpdatingFlag(false);

    public final static BoardPosition BOARD_POSITION_22_ALREADY_CLICKED =
            BOARD_POSITION_22.copyUpdatingAlreadyClicked(true);
}

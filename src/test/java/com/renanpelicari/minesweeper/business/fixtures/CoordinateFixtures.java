package com.renanpelicari.minesweeper.business.fixtures;

import com.renanpelicari.minesweeper.domain.model.Coordinate;

import java.util.Set;

public class CoordinateFixtures {

    public static final Coordinate COORDINATE_00 = new Coordinate(0, 0);
    public static final Coordinate COORDINATE_01 = new Coordinate(0, 1);
    public static final Coordinate COORDINATE_02 = new Coordinate(0, 2);
    public static final Coordinate COORDINATE_10 = new Coordinate(1, 0);
    public static final Coordinate COORDINATE_11 = new Coordinate(1, 1);
    public static final Coordinate COORDINATE_12 = new Coordinate(1, 2);
    public static final Coordinate COORDINATE_20 = new Coordinate(2, 0);
    public static final Coordinate COORDINATE_21 = new Coordinate(2, 1);
    public static final Coordinate COORDINATE_22 = new Coordinate(2, 2);

    public static final Set<Coordinate> COORDINATE_00_NEIGHBOURS = Set.of(
            COORDINATE_01,
            COORDINATE_10,
            COORDINATE_11
    );

    public static final Set<Coordinate> COORDINATE_01_NEIGHBOURS = Set.of(
            COORDINATE_00,
            COORDINATE_02,
            COORDINATE_10,
            COORDINATE_11,
            COORDINATE_12
    );

    public static final Set<Coordinate> COORDINATE_02_NEIGHBOURS = Set.of(
            COORDINATE_01,
            COORDINATE_11,
            COORDINATE_12
    );

    public static final Set<Coordinate> COORDINATE_10_NEIGHBOURS = Set.of(
            COORDINATE_00,
            COORDINATE_01,
            COORDINATE_11,
            COORDINATE_20,
            COORDINATE_21
    );

    public static final Set<Coordinate> COORDINATE_11_NEIGHBOURS = Set.of(
            COORDINATE_00,
            COORDINATE_01,
            COORDINATE_02,
            COORDINATE_10,
            COORDINATE_12,
            COORDINATE_20,
            COORDINATE_21,
            COORDINATE_22
    );

    public static final Set<Coordinate> COORDINATE_12_NEIGHBOURS = Set.of(
            COORDINATE_01,
            COORDINATE_02,
            COORDINATE_11,
            COORDINATE_21,
            COORDINATE_22
    );

    public static final Set<Coordinate> COORDINATE_20_NEIGHBOURS = Set.of(
            COORDINATE_10,
            COORDINATE_11,
            COORDINATE_21
    );

    public static final Set<Coordinate> COORDINATE_21_NEIGHBOURS = Set.of(
            COORDINATE_10,
            COORDINATE_11,
            COORDINATE_12,
            COORDINATE_20,
            COORDINATE_22
    );

    public static final Set<Coordinate> COORDINATE_22_NEIGHBOURS = Set.of(
            COORDINATE_11,
            COORDINATE_12,
            COORDINATE_21
    );
}

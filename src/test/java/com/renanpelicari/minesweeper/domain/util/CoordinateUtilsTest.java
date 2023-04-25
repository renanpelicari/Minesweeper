package com.renanpelicari.minesweeper.domain.util;

import com.renanpelicari.minesweeper.business.fixtures.CoordinateFixtures;
import com.renanpelicari.minesweeper.domain.model.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link CoordinateUtils}
 */
class CoordinateUtilsTest {

    private static final int HEIGHT = 3;
    private static final int WIDTH = 3;

    @Test
    @DisplayName("Given the upper left position, should return 3 neighbours")
    public void givenUpperLeftPosition_shouldReturn3Neighbours() {
        Set<Coordinate> coordinates =
                CoordinateUtils.getNeighboursCoordinates(CoordinateFixtures.COORDINATE_00, HEIGHT, WIDTH);
        assertEquals(CoordinateFixtures.COORDINATE_00_NEIGHBOURS, coordinates);
    }

    @Test
    @DisplayName("Given the upper right position, should return 3 neighbours")
    public void givenUpperRightPosition_shouldReturn3Neighbours() {
        Set<Coordinate> coordinates =
                CoordinateUtils.getNeighboursCoordinates(CoordinateFixtures.COORDINATE_02, HEIGHT, WIDTH);
        assertEquals(CoordinateFixtures.COORDINATE_02_NEIGHBOURS, coordinates);
    }

    @Test
    @DisplayName("Given the upper middle position, should return 5 neighbours")
    public void givenUpperMiddlePosition_shouldReturn5Neighbours() {
        Set<Coordinate> coordinates =
                CoordinateUtils.getNeighboursCoordinates(CoordinateFixtures.COORDINATE_01, HEIGHT, WIDTH);
        assertEquals(CoordinateFixtures.COORDINATE_01_NEIGHBOURS, coordinates);
    }

    @Test
    @DisplayName("Given middle left, should return 5 neighbours")
    public void givenMiddleLeftPosition_shouldReturn5Neighbours() {
        Set<Coordinate> coordinates =
                CoordinateUtils.getNeighboursCoordinates(CoordinateFixtures.COORDINATE_10, HEIGHT, WIDTH);
        assertEquals(CoordinateFixtures.COORDINATE_10_NEIGHBOURS, coordinates);
    }

    @Test
    @DisplayName("Given middle right, should return 5 neighbours")
    public void givenMiddleRightPosition_shouldReturn5Neighbours() {
        Set<Coordinate> coordinates =
                CoordinateUtils.getNeighboursCoordinates(CoordinateFixtures.COORDINATE_12, HEIGHT, WIDTH);
        assertEquals(CoordinateFixtures.COORDINATE_12_NEIGHBOURS, coordinates);
    }

    @Test
    @DisplayName("Given middle middle, should return 8 neighbours")
    public void givenMiddleMiddlePosition_shouldReturn8Neighbours() {
        Set<Coordinate> coordinates =
                CoordinateUtils.getNeighboursCoordinates(CoordinateFixtures.COORDINATE_11, HEIGHT, WIDTH);
        assertEquals(CoordinateFixtures.COORDINATE_11_NEIGHBOURS, coordinates);
    }

    @Test
    @DisplayName("Given the down left position, should return 3 neighbours")
    public void givenDownLeftPosition_shouldReturn3Neighbours() {
        Set<Coordinate> coordinates =
                CoordinateUtils.getNeighboursCoordinates(CoordinateFixtures.COORDINATE_20, HEIGHT, WIDTH);
        assertEquals(CoordinateFixtures.COORDINATE_20_NEIGHBOURS, coordinates);
    }

    @Test
    @DisplayName("Given the down right position, should return 3 neighbours")
    public void givenDownRightPosition_shouldReturn3Neighbours() {
        Set<Coordinate> coordinates =
                CoordinateUtils.getNeighboursCoordinates(CoordinateFixtures.COORDINATE_21, HEIGHT, WIDTH);
        assertEquals(CoordinateFixtures.COORDINATE_21_NEIGHBOURS, coordinates);
    }

    @Test
    @DisplayName("Given the down middle position, should return 5 neighbours")
    public void givenDownMiddlePosition_shouldReturn5Neighbours() {
        Set<Coordinate> coordinates =
                CoordinateUtils.getNeighboursCoordinates(CoordinateFixtures.COORDINATE_22, HEIGHT, WIDTH);
        assertEquals(CoordinateFixtures.COORDINATE_22_NEIGHBOURS, coordinates);
    }
}
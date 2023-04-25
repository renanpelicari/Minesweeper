package com.renanpelicari.minesweeper.domain.util;

import com.renanpelicari.minesweeper.domain.model.Coordinate;

import java.util.HashSet;
import java.util.Set;

/**
 * The util class for coordinate.
 */
public class CoordinateUtils {

    /**
     * Based on the coordinate and the size of grid, this method will generate all the neighbour coordinates.
     * @param coordinate the coordinate that will be used as reference to generate the neighbours.
     * @param height the height of the board/grid.
     * @param width the width of the board/grid.
     * @return the set containing all the neighbour coordinates.
     */
    public static Set<Coordinate> getNeighboursCoordinates(Coordinate coordinate, int height, int width) {
        Set<Coordinate> coordinateSet = new HashSet<>();

        int x = coordinate.x();
        int y = coordinate.y();

        // up
        if (x > 0) {
            coordinateSet.add(new Coordinate(x - 1, y));
        }

        // left
        if (y > 0) {
            coordinateSet.add(new Coordinate(x, y - 1));
        }

        // down
        if (x < height - 1) {
            coordinateSet.add(new Coordinate(x + 1, y));
        }

        // right
        if (y < width - 1) {
            coordinateSet.add(new Coordinate(x, y + 1));
        }

        // up left
        if (x > 0 && y > 0) {
            coordinateSet.add(new Coordinate(x - 1, y - 1));
        }

        // down right
        if (x < height - 1 && y < width - 1) {
            coordinateSet.add(new Coordinate(x + 1, y + 1));
        }

        // up right
        if (x > 0 && y < width - 1) {
            coordinateSet.add(new Coordinate(x - 1, y + 1));
        }

        // down left
        if (x < height - 1 && y > 0) {
            coordinateSet.add(new Coordinate(x + 1, y - 1));
        }

        return coordinateSet;
    }
}

package com.renanpelicari.minesweeper.domain.util;

import com.renanpelicari.minesweeper.domain.model.Coordinate;

import java.util.HashSet;
import java.util.Set;

public class CoordinateUtils {

    public static Set<Coordinate> getNeighboursCoordinates(Coordinate coordinate) {
        Set<Coordinate> coordinateSet = new HashSet<>();

        int x = coordinate.x();
        int y = coordinate.y();

        if (x > 0) {
            coordinateSet.add(new Coordinate(x - 1, y));
        }

        if (y > 0) {
            coordinateSet.add(new Coordinate(x, y - 1));
        }

        coordinateSet.add(new Coordinate(x + 1, y));
        coordinateSet.add(new Coordinate(x, y + 1));

        return coordinateSet;
    }
}

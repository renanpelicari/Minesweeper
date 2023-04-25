package com.renanpelicari.minesweeper.domain.util;

import com.renanpelicari.minesweeper.domain.model.BoardPosition;
import com.renanpelicari.minesweeper.domain.model.Coordinate;
import com.renanpelicari.minesweeper.infrastructure.config.MinesweeperConfig;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The handler to generate board and bombs position.
 */
public class MinesweeperBoardUtils {

    /**
     * Based on minesweeper configuration, like total bombs, width and height of the grid, this method will generate
     * a map containing the hashCode of coordinate as key, and a BoardPosition as value, that contains not only the
     * position itself, but more information related to that position, like: hasBomb, hasFlag, alreadyClicked,
     * totalNeighbourBombs
     * @param minesweeperConfig the configuration of the minesweeper game.
     * @return a map containing the hashCode of coordinate as key, and a BoardPosition as value.
     */
    public static Map<Integer, BoardPosition> generateBoardPositions(MinesweeperConfig minesweeperConfig) {

        Set<Coordinate> bombPositions = generateBombsPosition(minesweeperConfig);

        int height = minesweeperConfig.height();
        int width = minesweeperConfig.width();

        return IntStream.range(0, height)
                .boxed()
                .flatMap(row -> IntStream.range(0, width).mapToObj(col -> new Coordinate(row, col)))
                .collect(Collectors.toMap(
                        Record::hashCode,
                        coordinate -> {
                            boolean hasBomb = bombPositions.contains(coordinate);
                            Set<Coordinate> neighbourCandidates = CoordinateUtils.getNeighboursCoordinates(coordinate,
                                    height, width);

                            long totalNeighbourBombs = neighbourCandidates.stream().filter(bombPositions::contains).count();

                            return BoardPosition.builder()
                                    .coordinate(coordinate)
                                    .hasBomb(hasBomb)
                                    .hasFlag(false)
                                    .neighbourCoordinates(neighbourCandidates)
                                    .totalNeighbourBombs(totalNeighbourBombs)
                                    .build();
                        }));
    }

    private static Set<Coordinate> generateBombsPosition(MinesweeperConfig minesweeperConfig) {
        int totalBombs = minesweeperConfig.bombs();
        int width = minesweeperConfig.width();
        int height = minesweeperConfig.height();
        int gridPositions = width * height;

        Random random = new Random();

        return random.ints(0, gridPositions)
                .distinct()
                .limit(totalBombs)
                .mapToObj(i -> new Coordinate(i % width, i / width))
                .collect(Collectors.toSet());
    }

}

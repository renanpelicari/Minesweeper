package com.renanpelicari.minesweeper.business.usecase;

import com.renanpelicari.minesweeper.domain.model.BoardPosition;
import com.renanpelicari.minesweeper.domain.model.Coordinate;
import com.renanpelicari.minesweeper.domain.util.CoordinateUtils;
import com.renanpelicari.minesweeper.infrastructure.config.MinesweeperConfig;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GenerateBoardPositionMapUseCase {

    private final MinesweeperConfig minesweeperConfig;

    public GenerateBoardPositionMapUseCase(MinesweeperConfig minesweeperConfig) {
        this.minesweeperConfig = minesweeperConfig;
    }

    public Map<Integer, BoardPosition> exec(Set<Coordinate> bombPositions) {
        int height = minesweeperConfig.height();
        int width = minesweeperConfig.width();

        return IntStream.range(0, height)
                .boxed()
                .flatMap(row -> IntStream.range(0, width).mapToObj(col -> new Coordinate(row, col)))
                .collect(Collectors.toMap(
                        Record::hashCode,
                        coordinate -> {
                            boolean hasBomb = bombPositions.contains(coordinate);
                            Set<Coordinate> neighbourBombs = getNeighbourBombs(coordinate, bombPositions, height, width);
//                            Set<Coordinate> neighbourBombs = hasBomb ? Set.of() :
//                                    getNeighbourBombs(coordinate, bombPositions);

                            return BoardPosition.builder()
                                    .coordinate(coordinate)
                                    .hasBomb(hasBomb)
                                    .hasFlag(false)
                                    .neighbourBombs(neighbourBombs)
                                    .totalNeighbourBombs((int) neighbourBombs.stream().filter(bombPositions::contains).count())
//                                    .totalNeighbourBombs(neighbourBombs.size())
                                    .build();
                        }));
    }

    private Set<Coordinate> getNeighbourBombs(Coordinate coordinate, Set<Coordinate> bombPositions,
                                              int height, int width) {
        return CoordinateUtils.getNeighboursCoordinates(coordinate, height, width)
                .stream()
//                .filter(bombPositions::contains)
                .collect(Collectors.toSet());
    }
}

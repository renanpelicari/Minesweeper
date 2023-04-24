package com.renanpelicari.minesweeper.business.usecase;

import com.renanpelicari.minesweeper.domain.model.BoardPosition;
import com.renanpelicari.minesweeper.domain.model.Coordinate;
import com.renanpelicari.minesweeper.domain.util.CoordinateUtils;
import com.renanpelicari.minesweeper.infrastructure.config.MinesweeperConfig;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GenerateBoardPositionsUseCase {

    private final MinesweeperConfig minesweeperConfig;

    public GenerateBoardPositionsUseCase(MinesweeperConfig minesweeperConfig) {
        this.minesweeperConfig = minesweeperConfig;
    }

    public Set<BoardPosition> exec(Set<Coordinate> bombPositions) {
        int height = minesweeperConfig.height();
        int width = minesweeperConfig.width();

        return IntStream.range(0, height)
                .boxed()
                .flatMap(row -> IntStream.range(0, width).mapToObj(col -> new Coordinate(row, col)))
                .map(coordinate -> {
                    boolean hasBomb = bombPositions.contains(coordinate);
                    Set<Coordinate> neighbourBombs = hasBomb ? Set.of() : getNeighbourBombs(coordinate, bombPositions);

                    return BoardPosition.builder()
                            .coordinate(coordinate)
                            .hasBomb(hasBomb)
                            .hasFlag(false)
                            .neighbourBombs(neighbourBombs)
                            .totalNeighbourBombs(neighbourBombs.size())
                            .build();
                })
                .collect(Collectors.toSet());
    }

    private Set<Coordinate> getNeighbourBombs(Coordinate coordinate, Set<Coordinate> bombPositions) {
        return CoordinateUtils.getNeighboursCoordinates(coordinate)
                .stream()
                .filter(bombPositions::contains)
                .collect(Collectors.toSet());
    }
}

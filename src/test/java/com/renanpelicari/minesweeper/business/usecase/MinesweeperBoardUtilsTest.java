package com.renanpelicari.minesweeper.business.usecase;

import com.renanpelicari.minesweeper.domain.util.MinesweeperBoardUtils;
import com.renanpelicari.minesweeper.domain.model.Coordinate;
import com.renanpelicari.minesweeper.infrastructure.config.MinesweeperConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MinesweeperBoardUtilsTest {

    @InjectMocks
    private MinesweeperBoardUtils minesweeperBoardUtils;

    @Mock
    private MinesweeperConfig minesweeperConfig;

    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private static final int BOMBS = 5;

    @BeforeEach
    public void setUp() {
        // Mock the MinesweeperConfig object
        when(minesweeperConfig.width()).thenReturn(WIDTH);
        when(minesweeperConfig.height()).thenReturn(HEIGHT);
    }

    @Test
    public void shouldGeneratePositionsForAllGrid() {

        Set<Coordinate> bombPositions = Set.of(
                new Coordinate(1, 3),
                new Coordinate(1, 4),
                new Coordinate(2, 2),
                new Coordinate(3, 1),
                new Coordinate(4, 3)
        );

//        Map<Integer, BoardPosition> boardPositions = minesweeperBoardHandler.exec(bombPositions);

//        // Assert that each bomb position coordinate is within the grid size
//        assertEquals(25, boardPositions.size());
//        assertEquals(5, boardPositions.stream().filter(BoardPosition::hasBomb).count());
//
//        // Verify that the width(), height() were called on the MinesweeperConfig object
//        verify(minesweeperConfig).width();
//        verify(minesweeperConfig).height();
//        verify(minesweeperConfig, never()).bombs();
    }

//
//    private static final int WIDTH = 5;
//    private static final int HEIGHT = 5;
//    private static final int BOMBS = 5;
//
//    @BeforeEach
//    public void setUp() {
//        // Mock the MinesweeperConfig object
//        when(minesweeperConfig.width()).thenReturn(WIDTH);
//        when(minesweeperConfig.height()).thenReturn(HEIGHT);
//        when(minesweeperConfig.bombs()).thenReturn(BOMBS);
//    }
//
//    @Test
//    public void shouldGenerateUniqueBombPositionsBasedOnConfigAmount() {
//        // Call the exec() method to generate bomb positions
//        Set<Coordinate> bombPositions = generateBombPositionsUseCase.exec();
//
//        // Assert that the size of the generated bomb positions set is equal to the number of bombs
//        assertEquals(BOMBS, bombPositions.size());
//
//        // Assert that each bomb position coordinate is within the grid size
//        bombPositions.forEach(coordinate -> {
//            int x = coordinate.x();
//            int y = coordinate.y();
//            assertTrue(x >= 0 && x < WIDTH);
//            assertTrue(y >= 0 && y < HEIGHT);
//        });
//
//        // Verify that the width(), height(), and bombs() methods were called on the MinesweeperConfig object
//        verify(minesweeperConfig).width();
//        verify(minesweeperConfig).height();
//        verify(minesweeperConfig).bombs();
//    }
}
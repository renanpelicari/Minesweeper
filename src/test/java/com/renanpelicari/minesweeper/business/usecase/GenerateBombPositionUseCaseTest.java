package com.renanpelicari.minesweeper.business.usecase;

import com.renanpelicari.minesweeper.domain.model.Coordinate;
import com.renanpelicari.minesweeper.infrastructure.config.MinesweeperConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenerateBombPositionUseCaseTest {

    @InjectMocks
    private GenerateBombPositionUseCase generateBombPositionUseCase;

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
        when(minesweeperConfig.bombs()).thenReturn(BOMBS);
    }

    @Test
    public void shouldGenerateUniqueBombPositionsBasedOnConfigAmount() {
        // Call the exec() method to generate bomb positions
        Set<Coordinate> bombPositions = generateBombPositionUseCase.exec();

        // Assert that the size of the generated bomb positions set is equal to the number of bombs
        assertEquals(BOMBS, bombPositions.size());

        // Assert that each bomb position coordinate is within the grid size
        bombPositions.forEach(coordinate -> {
            int x = coordinate.x();
            int y = coordinate.y();
            assertTrue(x >= 0 && x < WIDTH);
            assertTrue(y >= 0 && y < HEIGHT);
        });

        // Verify that the width(), height(), and bombs() methods were called on the MinesweeperConfig object
        verify(minesweeperConfig).width();
        verify(minesweeperConfig).height();
        verify(minesweeperConfig).bombs();
    }
}
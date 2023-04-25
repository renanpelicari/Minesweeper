package com.renanpelicari.minesweeper.domain.util;

import com.renanpelicari.minesweeper.business.fixtures.MinesweeperFixtures;
import com.renanpelicari.minesweeper.domain.model.BoardPosition;
import com.renanpelicari.minesweeper.infrastructure.config.MinesweeperConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Unit test for {@link MinesweeperBoardUtils}
 */
class MinesweeperBoardUtilsTest {

    @Test
    @DisplayName("Given a minesweeper configuration, should return a map containing the board configuration")
    public void givenMinesweeperConfig_shouldReturnBoardConfiguration() {
        MinesweeperConfig config = MinesweeperFixtures.MINESWEEPER_CONFIG;
        Map<Integer, BoardPosition> result = MinesweeperBoardUtils.generateBoardPositions(config);

        int expectedSize = config.height() * config.width();
        assertEquals(expectedSize, result.size());

        int expectedBombs = config.bombs();
        int foundedBombs = (int) result.entrySet().stream().filter(entry -> entry.getValue().hasBomb()).count();
        // once the calc to generate the bombs consider can round, this assertion consider a range from -1 to + 1
        assertTrue(foundedBombs >= expectedBombs - 1 && foundedBombs <= expectedBombs + 1);
    }
}
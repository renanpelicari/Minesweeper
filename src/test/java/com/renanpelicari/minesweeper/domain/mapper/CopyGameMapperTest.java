package com.renanpelicari.minesweeper.domain.mapper;

import com.renanpelicari.minesweeper.business.fixtures.GameFixtures;
import com.renanpelicari.minesweeper.domain.model.CopyGame;
import com.renanpelicari.minesweeper.domain.model.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for {@link CopyGameMapper}
 */
class CopyGameMapperTest {

    @Test
    @DisplayName("Given a game, should return a copy game")
    public void givenGame_shouldReturnCopyGame() {
        CopyGame result = CopyGameMapper.gameToCopy(GameFixtures.STARTED_GAME);
        assertEquals(GameFixtures.COPY_GAME, result);
    }

    @Test
    @DisplayName("Given a copy game, should return a game")
    public void givenCopyGame_shouldReturnGame() {
        Game result = CopyGameMapper.copyToGame(GameFixtures.COPY_GAME);
        assertEquals(GameFixtures.STARTED_GAME, result);
    }
}
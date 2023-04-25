package com.renanpelicari.minesweeper.business.usecase;

import com.renanpelicari.minesweeper.business.fixtures.GameFixtures;
import com.renanpelicari.minesweeper.business.fixtures.MinesweeperFixtures;
import com.renanpelicari.minesweeper.domain.model.CopyGame;
import com.renanpelicari.minesweeper.domain.model.Game;
import com.renanpelicari.minesweeper.infrastructure.config.MinesweeperConfig;
import com.renanpelicari.minesweeper.infrastructure.repository.CopyGameRepository;
import com.renanpelicari.minesweeper.infrastructure.repository.GameRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


/**
 * Unit test for {@link StartNewGameUseCase}.
 */
@ExtendWith(MockitoExtension.class)
class StartNewGameUseCaseTest {

    @InjectMocks
    private StartNewGameUseCase startNewGameUseCase;

    @Mock
    private MinesweeperConfig minesweeperConfig;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private CopyGameRepository copyGameRepository;

    @Captor
    private ArgumentCaptor<Game> gameCaptor;

    @Captor
    private ArgumentCaptor<CopyGame> copyGameCaptor;

    @Test
    @DisplayName("Given a minesweeper config, when call to start new game, should return the Game entity")
    public void givenMinesweeperConfig_whenCallStartGame_shouldReturnGameEntity() {
        ReflectionTestUtils.setField(startNewGameUseCase, "minesweeperConfig", MinesweeperFixtures.MINESWEEPER_CONFIG);

        given(gameRepository.save(any())).willReturn(GameFixtures.STARTED_GAME);
        given(copyGameRepository.save(any())).willReturn(GameFixtures.COPY_GAME);

        startNewGameUseCase.exec();
        verify(gameRepository).save(gameCaptor.capture());
        verify(copyGameRepository).save(copyGameCaptor.capture());

        assertEquals(GameFixtures.NEW_GAME.id(), gameCaptor.getValue().id());
        assertEquals(GameFixtures.NEW_GAME.status(), gameCaptor.getValue().status());
        assertEquals(GameFixtures.NEW_GAME.totalBombs(), gameCaptor.getValue().totalBombs());
        assertEquals(GameFixtures.NEW_GAME.uncoveredCoordinates(), gameCaptor.getValue().uncoveredCoordinates());
        assertEquals(GameFixtures.COPY_GAME, copyGameCaptor.getValue());
    }
}
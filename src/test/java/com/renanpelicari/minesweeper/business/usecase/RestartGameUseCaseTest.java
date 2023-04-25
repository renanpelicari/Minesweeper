package com.renanpelicari.minesweeper.business.usecase;

import com.renanpelicari.minesweeper.business.fixtures.GameFixtures;
import com.renanpelicari.minesweeper.domain.exception.NotFoundException;
import com.renanpelicari.minesweeper.domain.model.Game;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit test for {@link RestartGameUseCase}.
 */
@ExtendWith(MockitoExtension.class)
class RestartGameUseCaseTest {

    @InjectMocks
    private RestartGameUseCase restartGameUseCase;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private CopyGameRepository copyGameRepository;

    @Captor
    private ArgumentCaptor<Game> gameCaptor;

    @Test
    @DisplayName("Given an invalid game id, should return NotFoundException")
    public void givenInvalidGameId_shouldReturnNotFoundException() {
        NotFoundException ex = assertThrows(NotFoundException.class, () ->
                        restartGameUseCase.exec("GAMEID"),
                "NotFoundException was expected when not found game by id.");

        assertEquals("A copy of Game could not be found by id=GAMEID", ex.getLocalizedMessage());
    }

    @Test
    @DisplayName("Given a valid game id, should return the copy of game")
    public void givenAValidGameId_shouldReturnTheCopyGame() {
        given(copyGameRepository.findById(GameFixtures.DEFAULT_ID))
                .willReturn(Optional.of(GameFixtures.COPY_GAME));

        restartGameUseCase.exec(GameFixtures.DEFAULT_ID);
        verify(gameRepository).save(gameCaptor.capture());

        assertEquals(GameFixtures.STARTED_GAME, gameCaptor.getValue());
    }
}
package com.renanpelicari.minesweeper.business.usecase;

import com.renanpelicari.minesweeper.business.fixtures.GameFixtures;
import com.renanpelicari.minesweeper.domain.exception.InvalidMovementException;
import com.renanpelicari.minesweeper.domain.exception.NotFoundException;
import com.renanpelicari.minesweeper.domain.model.Game;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit test for {@link ChangeFlagUseCase}.
 */
@ExtendWith(MockitoExtension.class)
class ChangeFlagUseCaseTest {

    @InjectMocks
    private ChangeFlagUseCase changeFlagUseCase;

    @Mock
    private GameRepository gameRepository;

    @Captor
    private ArgumentCaptor<Game> gameCaptor;

    @Test
    @DisplayName("Given an invalid game id, should return NotFoundException")
    public void givenInvalidGameId_shouldReturnNotFoundException() {
        given(gameRepository.findById(any())).willReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () ->
                        changeFlagUseCase.exec("GAMEID", 1, 1),
                "NotFoundException was expected when not found game by id.");

        assertEquals("Game not found by id=GAMEID", ex.getLocalizedMessage());

    }

    @Test
    @DisplayName("Given an invalid position, should return InvalidMovementException")
    public void givenInvalidPosition_shouldReturnInvalidMovementException() {
        given(gameRepository.findById(GameFixtures.DEFAULT_ID))
                .willReturn(Optional.of(GameFixtures.STARTED_GAME));

        InvalidMovementException ex = assertThrows(InvalidMovementException.class, () ->
                        changeFlagUseCase.exec(GameFixtures.DEFAULT_ID, 1, 3),
                "InvalidMovementException was expected when give an invalid position.");

        assertEquals("Position {Coordinate[x=1, y=3]} not exists in the game grid.", ex.getLocalizedMessage());
    }

    @Test
    @DisplayName("Given a game already finished, should return InvalidMovementException")
    public void givenFinishedGame_shouldReturnInvalidMovementException() {
        given(gameRepository.findById(GameFixtures.DEFAULT_ID))
                .willReturn(Optional.of(GameFixtures.FINISHED_GAME));

        InvalidMovementException ex = assertThrows(InvalidMovementException.class, () ->
                        changeFlagUseCase.exec(GameFixtures.DEFAULT_ID, 1, 1),
                "InvalidMovementException was expected when give a finished game.");

        assertEquals("Game DEFAULT_GAME_ID already finished, current status is PLAYER_WON.", ex.getLocalizedMessage());
    }

    @Test
    @DisplayName("Given a position already clicked, when try to add flag, should return InvalidMovementException")
    public void givenPositionClicked_whenAddFlag_shouldReturnInvalidMovementException() {
        given(gameRepository.findById(GameFixtures.DEFAULT_ID))
                .willReturn(Optional.of(GameFixtures.STARTED_GAME));

        InvalidMovementException ex = assertThrows(InvalidMovementException.class, () ->
                        changeFlagUseCase.exec(GameFixtures.DEFAULT_ID, 0, 0),
                "InvalidMovementException was expected when try to add a flag in a position which was clicked before.");

        assertEquals("Position {Coordinate[x=0, y=0]} was previously clicked.", ex.getLocalizedMessage());
    }

    @Test
    @DisplayName("Given a valid position and game, when remove flag, should return game with updated flag")
    public void givenValidGameAndPosition_whenRemoveFlag_shouldReturnUpdatedGame() {
        given(gameRepository.findById(GameFixtures.DEFAULT_ID))
                .willReturn(Optional.of(GameFixtures.STARTED_GAME_FLAG_CHANGED));

        given(gameRepository.save(GameFixtures.STARTED_GAME)).willReturn(GameFixtures.STARTED_GAME);

        Game expectedResult = GameFixtures.STARTED_GAME_FLAG_CHANGED;
        changeFlagUseCase.exec(GameFixtures.DEFAULT_ID, 2, 2);

        verify(gameRepository).findById(GameFixtures.DEFAULT_ID);
        verify(gameRepository).save(gameCaptor.capture());

        assertEquals(expectedResult, gameCaptor.getValue());

    }

    @Test
    @DisplayName("Given a valid position and game, when add flag, should return game with updated flag")
    public void givenValidGameAndPosition_whenAddFlag_shouldReturnUpdatedGame() {
        given(gameRepository.findById(GameFixtures.DEFAULT_ID))
                .willReturn(Optional.of(GameFixtures.STARTED_GAME));

        given(gameRepository.save(any())).willReturn(GameFixtures.STARTED_GAME_FLAG_CHANGED);

        Game expectedResult = GameFixtures.STARTED_GAME;
        changeFlagUseCase.exec(GameFixtures.DEFAULT_ID, 2, 2);

        verify(gameRepository).findById(GameFixtures.DEFAULT_ID);
        verify(gameRepository).save(gameCaptor.capture());

        assertEquals(expectedResult, gameCaptor.getValue());
    }
}
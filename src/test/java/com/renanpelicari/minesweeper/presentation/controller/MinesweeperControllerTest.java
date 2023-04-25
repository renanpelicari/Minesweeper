package com.renanpelicari.minesweeper.presentation.controller;

import com.renanpelicari.minesweeper.business.fixtures.GameFixtures;
import com.renanpelicari.minesweeper.business.fixtures.PayloadFixtures;
import com.renanpelicari.minesweeper.business.usecase.ChangeFlagUseCase;
import com.renanpelicari.minesweeper.business.usecase.PerformMovementUseCase;
import com.renanpelicari.minesweeper.business.usecase.RestartGameUseCase;
import com.renanpelicari.minesweeper.business.usecase.StartNewGameUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit test for {@link MinesweeperController}
 */
@WebMvcTest(MinesweeperController.class)
class MinesweeperControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StartNewGameUseCase startNewGameUseCase;

    @MockBean
    private RestartGameUseCase restartGameUseCase;

    @MockBean
    private ChangeFlagUseCase changeFlagUseCase;

    @MockBean
    private PerformMovementUseCase performMovementUseCase;


    @Test
    @DisplayName("When call endpoint /minesweeper/new endpoint, should return a new minesweeper Game")
    public void whenCallNewGameEndpoint_shouldReturnNewMinesweeperGame() throws Exception {
        given(startNewGameUseCase.exec()).willReturn(GameFixtures.STARTED_GAME);

        mockMvc.perform(MockMvcRequestBuilders.get("/minesweeper/new")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(PayloadFixtures.STARTED_GAME_RESPONSE));
        verify(startNewGameUseCase).exec();
    }

    @Test
    @DisplayName("When call endpoint /minesweeper/restart/id endpoint, should return a restarted minesweeper Game")
    public void whenCallRestartGameEndpoint_shouldReturnNewMinesweeperGame() throws Exception {
        given(restartGameUseCase.exec(GameFixtures.DEFAULT_ID)).willReturn(GameFixtures.STARTED_GAME);

        mockMvc.perform(MockMvcRequestBuilders.get("/minesweeper/restart/DEFAULT_GAME_ID")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(PayloadFixtures.STARTED_GAME_RESPONSE));
        verify(restartGameUseCase).exec(GameFixtures.DEFAULT_ID);
    }

    @Test
    @DisplayName("When call endpoint /minesweeper/click/id endpoint, should return a updated minesweeper Game")
    public void whenCallClickEndpoint_shouldReturnNewMinesweeperGame() throws Exception {
        given(performMovementUseCase.exec(GameFixtures.DEFAULT_ID, 1, 1)).willReturn(GameFixtures.STARTED_GAME);

        String uri = "/minesweeper/click/DEFAULT_GAME_ID?positionX=1&positionY=1";

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(PayloadFixtures.STARTED_GAME_RESPONSE));

        verify(performMovementUseCase).exec(GameFixtures.DEFAULT_ID, 1, 1);
    }


    @Test
    @DisplayName("When call endpoint /minesweeper/flag/id endpoint, should return a updated minesweeper Game")
    public void whenCallFlagEndpoint_shouldReturnNewMinesweeperGame() throws Exception {
        given(changeFlagUseCase.exec(GameFixtures.DEFAULT_ID, 1, 1)).willReturn(GameFixtures.STARTED_GAME);

        String uri = "/minesweeper/flag/DEFAULT_GAME_ID?positionX=1&positionY=1";

        mockMvc.perform(MockMvcRequestBuilders.put(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(PayloadFixtures.STARTED_GAME_RESPONSE));

        verify(changeFlagUseCase).exec(GameFixtures.DEFAULT_ID, 1, 1);
    }
}
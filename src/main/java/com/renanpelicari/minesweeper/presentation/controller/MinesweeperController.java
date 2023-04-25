package com.renanpelicari.minesweeper.presentation.controller;

import com.renanpelicari.minesweeper.business.usecase.ChangeFlagUseCase;
import com.renanpelicari.minesweeper.business.usecase.PerformMovementUseCase;
import com.renanpelicari.minesweeper.business.usecase.RestartGameUseCase;
import com.renanpelicari.minesweeper.business.usecase.StartNewGameUseCase;
import com.renanpelicari.minesweeper.domain.exception.InvalidMovementException;
import com.renanpelicari.minesweeper.domain.exception.NotFoundException;
import com.renanpelicari.minesweeper.domain.model.Game;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * The rest controller for minesweeper operations.
 */
@RestController
@RequestMapping(value = "/minesweeper", produces = MediaType.APPLICATION_JSON_VALUE)
public class MinesweeperController {

    private final StartNewGameUseCase startNewGameUseCase;

    private final RestartGameUseCase restartGameUseCase;

    private final ChangeFlagUseCase changeFlagUseCase;

    private final PerformMovementUseCase performMovementUseCase;

    public MinesweeperController(StartNewGameUseCase startNewGameUseCase,
                                 RestartGameUseCase restartGameUseCase,
                                 ChangeFlagUseCase changeFlagUseCase,
                                 PerformMovementUseCase performMovementUseCase) {
        this.startNewGameUseCase = startNewGameUseCase;
        this.restartGameUseCase = restartGameUseCase;
        this.changeFlagUseCase = changeFlagUseCase;
        this.performMovementUseCase = performMovementUseCase;
    }

    /**
     * This is the entrypoint to create a new game.
     * @return {@link Game} the model contain the game information.
     */
    @GetMapping(value = "/new")
    @Operation(summary = "Start new game.")
    public Game newGame() {
        return startNewGameUseCase.exec();
    }

    /**
     * The entrypoint for restart the existence game.
     * @param gameId the unique game id.
     * @return {@link Game} the model contain the game information.
     * @throws NotFoundException when the game cannot be found by gameId
     */
    @GetMapping(value = "/restart/{id}")
    public Game restartGame(@PathVariable("id") String gameId) throws NotFoundException {
        return restartGameUseCase.exec(gameId);
    }

    /**
     * The entrypoint for perform a movement / click on a board position.
     * @param gameId the unique game id.
     * @param positionX the x position of the coordinate
     * @param positionY the y position of the coordinate
     * @return {@link Game} the model contain the game information.
     * @throws NotFoundException when the game cannot be found by gameId
     * @throws InvalidMovementException when select a position which not exists
     * @throws InvalidMovementException when try to perform a movement in a finished game
     * @throws InvalidMovementException when try to click in a position that has a flag
     */
    @PutMapping(value = "/click/{gameId}")
    public Game onClick(@PathVariable("gameId") String gameId, @RequestParam int positionX,
                        @RequestParam int positionY) throws NotFoundException, InvalidMovementException {
        return performMovementUseCase.exec(gameId, positionX, positionY);
    }

    /**
     * The entrypoint to add/remove a flag in the board position.
     * @param gameId the unique game id.
     * @param positionX the x position of the coordinate
     * @param positionY the y position of the coordinate
     * @return {@link Game} the model contain the game information.
     * @throws NotFoundException when the game cannot be found by gameId
     * @throws InvalidMovementException when select a position which not exists
     * @throws InvalidMovementException when try to change a flag of finished game
     * @throws InvalidMovementException when try to change a flag of a position which previous selected (not as a flag)
     */
    @PutMapping(value = "/flag/{id}")
    public Game changeFlag(@PathVariable("id") String gameId, @RequestParam int positionX,
                           @RequestParam int positionY) throws NotFoundException, InvalidMovementException {
        return changeFlagUseCase.exec(gameId, positionX, positionY);
    }
}

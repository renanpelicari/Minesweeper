package com.renanpelicari.minesweeper.presentation.controller;

import com.renanpelicari.minesweeper.business.usecase.RestartGameUseCase;
import com.renanpelicari.minesweeper.business.usecase.StartNewGameUseCase;
import com.renanpelicari.minesweeper.business.strategy.PerformMovementStrategy;
import com.renanpelicari.minesweeper.domain.model.Game;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/minesweeper", produces = MediaType.APPLICATION_JSON_VALUE)
public class MinesweeperController {

    private final StartNewGameUseCase startNewGameUseCase;

    private final RestartGameUseCase restartGameUseCase;

    private final PerformMovementStrategy performMovementStrategy;

    public MinesweeperController(StartNewGameUseCase startNewGameUseCase,
                                 RestartGameUseCase restartGameUseCase,
                                 PerformMovementStrategy performMovementStrategy) {
        this.startNewGameUseCase = startNewGameUseCase;
        this.restartGameUseCase = restartGameUseCase;
        this.performMovementStrategy = performMovementStrategy;
    }


    @GetMapping(value = "/new")
    @Operation(summary = "Start new game.")
    public Game newGame() {
        return startNewGameUseCase.exec();
    }

    @GetMapping(value = "/restart/{id}")
    public Game restartGame(@PathVariable("id") String gameId) {
        return restartGameUseCase.exec(gameId);
    }

    @PutMapping(value = "/click/{gameId}")
    public Game onClick(@PathVariable("gameId") String gameId, @RequestParam int positionX,
                        @RequestParam int positionY) {
        return performMovementStrategy.exec(gameId, positionX, positionY);
    }
//
//    @PutMapping(value = "/flag/{id}")
//    public Game changeFlag(@PathVariable("id") Long gameId, @RequestParam int positionX,
//                                    @RequestParam int positionY) {
//        return null;
//    }
}

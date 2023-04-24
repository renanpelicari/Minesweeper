package com.renanpelicari.minesweeper.presentation.controller;

import com.renanpelicari.minesweeper.business.strategy.StartNewGameStrategy;
import com.renanpelicari.minesweeper.domain.model.Game;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/minesweeper", produces = MediaType.APPLICATION_JSON_VALUE)
public class MinesweeperController {

    private final StartNewGameStrategy startNewGameStrategy;

    public MinesweeperController(StartNewGameStrategy startNewGameStrategy) {
        this.startNewGameStrategy = startNewGameStrategy;
    }


    @GetMapping(value = "/new")
    @Operation(summary = "Start new game.")
    public Game newGame() {
        return startNewGameStrategy.exec();
    }
//
//    @GetMapping(value = "/restart/{id}")
//    public Game restartGame(@PathVariable("id") Long gameId) {
//        return null;
//    }
//
//    @PutMapping(value = "/click/{id}")
//    public Game onClick(@PathVariable("id") Long gameId, @RequestParam int positionX,
//                                 @RequestParam int positionY) {
//        return null;
//    }
//
//    @PutMapping(value = "/flag/{id}")
//    public Game changeFlag(@PathVariable("id") Long gameId, @RequestParam int positionX,
//                                    @RequestParam int positionY) {
//        return null;
//    }
}

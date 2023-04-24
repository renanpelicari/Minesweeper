package com.renanpelicari.minesweeper.presentation.controller;

import com.renanpelicari.minesweeper.domain.model.Game;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/minesweeper")
public class MinesweeperController {


    @GetMapping(value = "/new")
    public Game newGame() {
        return null;
    }

    @GetMapping(value = "/restart/{id}")
    public Game restartGame(@PathVariable("id") Long gameId) {
        return null;
    }

    @PutMapping(value = "/click/{id}")
    public Game onClick(@PathVariable("id") Long gameId, @RequestParam int positionX,
                                 @RequestParam int positionY) {
        return null;
    }

    @PutMapping(value = "/flag/{id}")
    public Game changeFlag(@PathVariable("id") Long gameId, @RequestParam int positionX,
                                    @RequestParam int positionY) {
        return null;
    }
}

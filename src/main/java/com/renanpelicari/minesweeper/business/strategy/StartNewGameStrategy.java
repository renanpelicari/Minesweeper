package com.renanpelicari.minesweeper.business.strategy;

import com.renanpelicari.minesweeper.business.usecase.GenerateBoardPositionsUseCase;
import com.renanpelicari.minesweeper.business.usecase.GenerateBombPositionsUseCase;
import com.renanpelicari.minesweeper.domain.model.BoardPosition;
import com.renanpelicari.minesweeper.domain.model.Coordinate;
import com.renanpelicari.minesweeper.domain.model.Game;
import com.renanpelicari.minesweeper.domain.model.GameStatus;
import com.renanpelicari.minesweeper.infrastructure.config.MinesweeperConfig;
import com.renanpelicari.minesweeper.infrastructure.repository.GameRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Log4j2
public class StartNewGameStrategy {

    private final MinesweeperConfig minesweeperConfig;

    private final GenerateBombPositionsUseCase generateBombPositionsUseCase;

    private final GenerateBoardPositionsUseCase generateBoardPositionsUseCase;

    private final GameRepository gameRepository;

    public StartNewGameStrategy(MinesweeperConfig minesweeperConfig,
                                GenerateBombPositionsUseCase generateBombPositionsUseCase,
                                GenerateBoardPositionsUseCase generateBoardPositionsUseCase,
                                GameRepository gameRepository) {
        this.minesweeperConfig = minesweeperConfig;
        this.generateBombPositionsUseCase = generateBombPositionsUseCase;
        this.generateBoardPositionsUseCase = generateBoardPositionsUseCase;
        this.gameRepository = gameRepository;
    }

    @Transactional
    public Game exec() {
        log.info("BEGIN startNewGame strategy.");

        int uncoveredCoordinates = minesweeperConfig.height() * minesweeperConfig.width();
        Set<Coordinate> bombPositions = generateBombPositionsUseCase.exec();
        Set<BoardPosition> boardPositions = generateBoardPositionsUseCase.exec(bombPositions);

        Game game = Game.builder()
                .status(GameStatus.STARTED)
                .totalBombs(minesweeperConfig.bombs())
                .uncoveredCoordinates(uncoveredCoordinates)
                .boardPositions(boardPositions)
                .build();

        Game savedGame = gameRepository.save(game);

        log.info("END startNewGame strategy, response={}", savedGame);
        return savedGame;

    }

}

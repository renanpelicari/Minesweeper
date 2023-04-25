package com.renanpelicari.minesweeper.business.usecase;

import com.renanpelicari.minesweeper.domain.util.MinesweeperBoardUtils;
import com.renanpelicari.minesweeper.domain.mapper.CopyGameMapper;
import com.renanpelicari.minesweeper.domain.model.BoardPosition;
import com.renanpelicari.minesweeper.domain.model.Game;
import com.renanpelicari.minesweeper.domain.model.GameStatus;
import com.renanpelicari.minesweeper.infrastructure.config.MinesweeperConfig;
import com.renanpelicari.minesweeper.infrastructure.repository.CopyGameRepository;
import com.renanpelicari.minesweeper.infrastructure.repository.GameRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * The starting new game use case.
 */
@Log4j2
@Service
public class StartNewGameUseCase {

    private final MinesweeperConfig minesweeperConfig;

    private final GameRepository gameRepository;

    private final CopyGameRepository copyGameRepository;

    public StartNewGameUseCase(MinesweeperConfig minesweeperConfig,
                               GameRepository gameRepository,
                               CopyGameRepository copyGameRepository) {
        this.minesweeperConfig = minesweeperConfig;
        this.gameRepository = gameRepository;
        this.copyGameRepository = copyGameRepository;
    }

    /**
     * This method will create a new minesweeper game with bombs in a random position, also will generate a copy of
     * this game in the initial state, to be used in case of restart game.
     * @return the {@link Game} containing the all setup minesweeper game.
     */
    @Transactional
    public Game exec() {
        log.info("BEGIN startNewGame.");

        int uncoveredCoordinates = (minesweeperConfig.height() * minesweeperConfig.width()) - minesweeperConfig.bombs();
        Map<Integer, BoardPosition> boardPositionMap = MinesweeperBoardUtils.generateBoardPositions(minesweeperConfig);

        Game game = Game.builder()
                .status(GameStatus.STARTED)
                .totalBombs(minesweeperConfig.bombs())
                .uncoveredCoordinates(uncoveredCoordinates)
                .boardPositionMap(boardPositionMap)
                .build();

        Game savedGame = gameRepository.save(game);
        copyGameRepository.save(CopyGameMapper.gameToCopy(savedGame));

        log.info("END startNewGame, response={}", savedGame);
        return savedGame;

    }

}

package com.renanpelicari.minesweeper.business.usecase;

import com.renanpelicari.minesweeper.domain.exception.NotFoundException;
import com.renanpelicari.minesweeper.domain.mapper.CopyGameMapper;
import com.renanpelicari.minesweeper.domain.model.CopyGame;
import com.renanpelicari.minesweeper.domain.model.Game;
import com.renanpelicari.minesweeper.infrastructure.repository.CopyGameRepository;
import com.renanpelicari.minesweeper.infrastructure.repository.GameRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class RestartGameUseCase {

    private final GameRepository gameRepository;

    private final CopyGameRepository copyGameRepository;

    public RestartGameUseCase(GameRepository gameRepository, CopyGameRepository copyGameRepository) {
        this.gameRepository = gameRepository;
        this.copyGameRepository = copyGameRepository;
    }

    @Transactional
    public Game exec(String gameId) {
        log.info("BEGIN restartGame strategy.");

        CopyGame copyGame = copyGameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException(String.format("A copy of Game not found by id=%s", gameId)));


        Game updatedGame = gameRepository.save(CopyGameMapper.copyToGame(copyGame));

        log.info("END restartGame strategy, response={}", updatedGame);
        return updatedGame;

    }

}

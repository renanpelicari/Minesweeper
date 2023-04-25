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

/**
 * This use case is responsible to restart an exists game.
 */
@Log4j2
@Service
public class RestartGameUseCase {

    private final GameRepository gameRepository;

    private final CopyGameRepository copyGameRepository;

    public RestartGameUseCase(GameRepository gameRepository, CopyGameRepository copyGameRepository) {
        this.gameRepository = gameRepository;
        this.copyGameRepository = copyGameRepository;
    }

    /**
     * Based on gameId, this method will search for the copy of the game, in a different repository, then override
     * the current version of the game in the main repository.
     * @param gameId the game unique identification
     * @return the {@link Game} with all configuration (bombs and board positions), but all movements reset.
     * @throws NotFoundException when the game cannot be found by gameId
     */
    @Transactional
    public Game exec(String gameId) throws NotFoundException {
        log.info("BEGIN restartGame strategy.");

        CopyGame copyGame = copyGameRepository.findById(gameId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("A copy of Game could not be found by id=%s", gameId)));

        Game updatedGame = gameRepository.save(CopyGameMapper.copyToGame(copyGame));

        log.info("END restartGame strategy, response={}", updatedGame);
        return updatedGame;

    }

}

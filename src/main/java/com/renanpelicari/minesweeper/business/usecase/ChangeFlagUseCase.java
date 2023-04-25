package com.renanpelicari.minesweeper.business.usecase;

import com.renanpelicari.minesweeper.business.validator.MovementValidator;
import com.renanpelicari.minesweeper.domain.exception.NotFoundException;
import com.renanpelicari.minesweeper.domain.model.BoardPosition;
import com.renanpelicari.minesweeper.domain.model.Coordinate;
import com.renanpelicari.minesweeper.domain.model.Game;
import com.renanpelicari.minesweeper.infrastructure.repository.GameRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Log4j2
public class ChangeFlagUseCase {

    private final GameRepository gameRepository;


    public ChangeFlagUseCase(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public Game exec(String gameId, int x, int y) {
        log.info("BEGIN changeFlag.");
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException(String.format("Game not found by id=%s", gameId)));

        // TODO: starting duplicated part with PerformMovementStrategy - need to be refactored and reused for both
        // verify the status of game
        MovementValidator.validateGameAlreadyFinished(game);

        // new clicked position
        Coordinate coordinate = new Coordinate(x, y);
        Integer coordinateKey = coordinate.hashCode();

        // get the positions in the grid
        Map<Integer, BoardPosition> boardPositionMap = game.boardPositionMap();

        // verify if the position is valid
        MovementValidator.validatePositionIsValid(boardPositionMap, coordinate);
        BoardPosition boardPositionClicked = boardPositionMap.get(coordinateKey);

        // verify if the position was clicked before
        MovementValidator.validatePositionAlreadyClicked(boardPositionClicked, coordinate);
        // TODO: finishing duplicated part with PerformMovementStrategy - need to be refactored and reused for both

        // generate new object from an exists one and update the map
        BoardPosition boardPositionToUpdate = boardPositionClicked.copyUpdatingFlag(!boardPositionClicked.hasFlag());
        boardPositionMap.put(coordinateKey, boardPositionToUpdate);

        Game gameToUpdate = game.copyUpdatingMovement(boardPositionMap);

        Game savedGame = gameRepository.save(gameToUpdate);

        log.info("END changeFlag, response={}", savedGame);
        return savedGame;

    }
}

package com.renanpelicari.minesweeper.business.usecase;

import com.renanpelicari.minesweeper.domain.exception.InvalidMovementException;
import com.renanpelicari.minesweeper.domain.exception.NotFoundException;
import com.renanpelicari.minesweeper.domain.model.BoardPosition;
import com.renanpelicari.minesweeper.domain.model.Coordinate;
import com.renanpelicari.minesweeper.domain.model.Game;
import com.renanpelicari.minesweeper.domain.validator.MovementValidator;
import com.renanpelicari.minesweeper.infrastructure.repository.GameRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * The use case to handle with add/remove flag.
 */
@Log4j2
@Service
public class ChangeFlagUseCase {

    private final GameRepository gameRepository;


    public ChangeFlagUseCase(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * Based on gameId and x,y position, this method will perform a change in the attribute flag.
     * When the current flag is true, will change to false, when it's false will change to true.
     * @param gameId the game unique identification
     * @param x the coordinate x
     * @param y the coordinate y
     * @return the updated {@link Game} containing the all setup of game with the movements, positions and status.
     * @throws NotFoundException when the game cannot be found by gameId
     * @throws InvalidMovementException when select a position which not exists
     * @throws InvalidMovementException when try to change a flag of finished game
     * @throws InvalidMovementException when try to change a flag of a position which previous selected (not as a flag)
     */
    @Transactional
    public Game exec(String gameId, int x, int y) throws NotFoundException, InvalidMovementException {
        log.info("BEGIN changeFlag.");
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException(String.format("Game not found by id=%s", gameId)));

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

        // generate new object from an exists one and update the map
        BoardPosition boardPositionToUpdate = boardPositionClicked.copyUpdatingFlag(!boardPositionClicked.hasFlag());
        boardPositionMap.put(coordinateKey, boardPositionToUpdate);

        Game gameToUpdate = game.copyUpdatingMovement(boardPositionMap);

        Game savedGame = gameRepository.save(gameToUpdate);

        log.info("END changeFlag, response={}", savedGame);
        return savedGame;
    }
}

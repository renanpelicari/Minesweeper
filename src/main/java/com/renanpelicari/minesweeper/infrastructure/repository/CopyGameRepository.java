package com.renanpelicari.minesweeper.infrastructure.repository;

import com.renanpelicari.minesweeper.domain.model.CopyGame;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface for CopyGame MongoRepository, that store the copy of initial state of Game.
 */
@Repository
public interface CopyGameRepository extends MongoRepository<CopyGame, String> {
}

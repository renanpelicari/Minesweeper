package com.renanpelicari.minesweeper.infrastructure.repository;

import com.renanpelicari.minesweeper.domain.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface for Game MongoRepository.
 */
@Repository
public interface GameRepository extends MongoRepository<Game, String> {
}

package com.renanpelicari.minesweeper.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * InvalidMovementException is a class which extends RuntimeException, that can be used for the invalid movements in
 * this API.
 * Since this class extends a RuntimeException, will be not a blocker for the application.
 * This also returns http 400 for API.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidMovementException extends RuntimeException {

    public InvalidMovementException(String message) {
        super(message);
    }
}

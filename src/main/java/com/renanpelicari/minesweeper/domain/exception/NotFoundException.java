package com.renanpelicari.minesweeper.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * InvalidMovementException is a class which extends RuntimeException, that can be used for the invalid movements in
 * this API.
 * Since this class extends a RuntimeException, will be not a blocker for the application.
 * This also returns http 404 for API.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}

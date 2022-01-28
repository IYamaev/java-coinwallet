package org.example.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CoinNotFoundException extends RuntimeException {
    public CoinNotFoundException() {
    }

    public CoinNotFoundException(String message) {
        super(message);
    }

    public CoinNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoinNotFoundException(Throwable cause) {
        super(cause);
    }

    public CoinNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

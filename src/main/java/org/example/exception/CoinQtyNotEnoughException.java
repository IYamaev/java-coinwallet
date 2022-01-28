package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CoinQtyNotEnoughException extends RuntimeException {
    public CoinQtyNotEnoughException() {
    }

    public CoinQtyNotEnoughException(String message) {
        super(message);
    }

    public CoinQtyNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoinQtyNotEnoughException(Throwable cause) {
        super(cause);
    }

    public CoinQtyNotEnoughException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

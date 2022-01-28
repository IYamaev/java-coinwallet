package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CoinPriceChangeException extends RuntimeException {
    public CoinPriceChangeException() {
    }

    public CoinPriceChangeException(String message) {
        super(message);
    }

    public CoinPriceChangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoinPriceChangeException(Throwable cause) {
        super(cause);
    }

    public CoinPriceChangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

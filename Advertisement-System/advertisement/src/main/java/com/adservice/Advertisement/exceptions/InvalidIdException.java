package com.adservice.Advertisement.exceptions;// package com.inventory.Order.exception;

public class InvalidIdException extends Exception {

    public InvalidIdException() {
        super();
    }

    public InvalidIdException(String message) {
        super(message);
    }

    public InvalidIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidIdException(Throwable cause) {
        super(cause);
    }

    protected InvalidIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

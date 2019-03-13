package org.laziji.commons.security.exception;

public class FormErrorException extends Exception {

    public FormErrorException() {
        super();
    }

    public FormErrorException(String message) {
        super(message);
    }

    public FormErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormErrorException(Throwable cause) {
        super(cause);
    }

    protected FormErrorException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com.ynnckth.ddddemo.core.application.exception;

public class MissingExchangeRateException extends RuntimeException {

    public MissingExchangeRateException(String message) {
        super(message);
    }
}

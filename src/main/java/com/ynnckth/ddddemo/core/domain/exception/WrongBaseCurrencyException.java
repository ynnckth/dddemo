package com.ynnckth.ddddemo.core.domain.exception;

public class WrongBaseCurrencyException extends RuntimeException {

    public WrongBaseCurrencyException(String message) {
        super(message);
    }
}

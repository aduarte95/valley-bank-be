package com.backend.bank.exception;

public class RepeatedValueException
        extends RuntimeException {

    public RepeatedValueException(String errorMessage) {
        super(errorMessage);
    }
}




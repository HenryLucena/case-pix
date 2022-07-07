package com.example.demo.exceptionHandler;

public class ChavePixInvalidaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ChavePixInvalidaException(String e){
        super(e);
    }

    public ChavePixInvalidaException(String e, Throwable throwable){
        super(e, throwable);
    }
}

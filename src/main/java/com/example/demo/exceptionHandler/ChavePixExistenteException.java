package com.example.demo.exceptionHandler;

public class ChavePixExistenteException extends RuntimeException{

    public ChavePixExistenteException(String e){
        super(e);
    }

    public ChavePixExistenteException(String e, Throwable throwable){
        super(e, throwable);
    }
}

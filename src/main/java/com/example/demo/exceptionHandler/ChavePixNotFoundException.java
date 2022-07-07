package com.example.demo.exceptionHandler;

public class ChavePixNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ChavePixNotFoundException(String e){super(e);}

    public ChavePixNotFoundException(String e, Throwable throwable){super(e, throwable);}
}

package com.example.helloWorld.error;

public class BusinessException extends RuntimeException {
    public BusinessException(){

    }

    public BusinessException(String msg){
        super(msg);
    }
}

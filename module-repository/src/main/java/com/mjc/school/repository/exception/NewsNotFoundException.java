package com.mjc.school.repository.exception;

public class NewsNotFoundException extends RuntimeException {
    public NewsNotFoundException(String msg){
        super(msg);
    }

}

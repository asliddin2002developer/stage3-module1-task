package com.mjc.school.repository.exception;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(String msg){
        super(msg);
    }
}

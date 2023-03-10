package com.mjc.school.service.enums;

public enum Validators {

    TITLE_LENGTH_VALIDATOR("Title field should have length of value from 5 to 30"),
    CONTENT_LENGTH_VALIDATOR("Content field should have length of value from 5 to 255"),
    AUTHOR_LENGTH_VALIDATOR("Author length should be 5-15 characters"),
    AUTHOR_NOT_FOUND_VALIDATOR("AuthorId should be mapped to the author datasource");
    private final String errorMessage;

     Validators(String s) {
        this.errorMessage = s;
    }
}

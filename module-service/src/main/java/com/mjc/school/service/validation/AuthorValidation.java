package com.mjc.school.service.validation;

import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.repository.exception.LengthIsNotValidException;

public class AuthorValidation {
    String AUTHOR_NAME_LENGTH = "Length should be between 3-15";

    public boolean isValidAuthorParams(AuthorDto authorDto){
        if (authorDto.getName().length() < 3 || authorDto.getName().length() > 15){
            System.err.println("Author name length is not valid");
        }
        return true;
    }

}
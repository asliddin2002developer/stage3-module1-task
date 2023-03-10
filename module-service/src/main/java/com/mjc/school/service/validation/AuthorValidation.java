package com.mjc.school.service.validation;

import com.mjc.school.service.dto.AuthorDto;

public class AuthorValidation {

    public boolean isValidAuthorParams(AuthorDto authorDto){
        if (authorDto.getName().length() < 3 || authorDto.getName().length() > 15){
            System.err.println("Author name length is not valid");
            return false;
        }
        return true;
    }

}
package com.mjc.school.service.validation;

import com.mjc.school.service.dto.AuthorDto;

public class AuthorValidation {
    String AUTHOR_NAME_LENGTH = "Length should be between 3-15";

    public boolean isValidAuthorParams(AuthorDto authorDto){
        if (authorDto.getName().length() < 3 || authorDto.getName().length() > 15){
            System.out.println(AUTHOR_NAME_LENGTH);
            return false;
        }
        return true;
    }

}
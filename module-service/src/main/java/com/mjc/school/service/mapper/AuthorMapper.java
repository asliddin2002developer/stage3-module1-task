package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.dto.AuthorDto;

public class AuthorMapper {

    public AuthorDto toDto(AuthorModel author){
        Long id = author.getId();
        String name = author.getName();
        return new AuthorDto(id, name);
    }

    public AuthorModel toModel(AuthorDto author){
        Long id = author.getId();
        String name = author.getName();
        return new AuthorModel(id, name);
    }
}

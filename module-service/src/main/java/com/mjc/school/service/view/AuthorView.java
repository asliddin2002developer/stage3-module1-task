package com.mjc.school.service.view;

import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.impl.AuthorService;

import java.util.List;

public class AuthorView {
    private List<AuthorDto> allAuthors;
    private AuthorService authorService;

    public AuthorView(){
        authorService = AuthorService.getInstance();
        allAuthors = authorService.readAll();
    }

    public void display(){
        update();
        allAuthors.forEach(System.out::println);
    }

    public void update(){
        authorService = AuthorService.getInstance();
        allAuthors = authorService.readAll();
    }
}

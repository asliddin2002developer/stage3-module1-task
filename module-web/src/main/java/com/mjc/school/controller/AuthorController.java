package com.mjc.school.controller;

import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.impl.AuthorService;
import com.mjc.school.service.view.AuthorView;

public class AuthorController {
    private final AuthorService model;
    private final AuthorView authorView;

    public AuthorController(AuthorService authorService){
        model = authorService;
        authorView = new AuthorView(authorService);
    }

    public void showView(){
        authorView.display();
    }

    public void update(AuthorDto authorDto){
            model.update(authorDto);
    }

    public void create(AuthorDto authorDto){
        model.create(authorDto);
    }


}

package com.mjc.school.controller;

import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.exception.AuthorNotFoundException;
import com.mjc.school.service.exception.CharacterLengthIsNotValidException;
import com.mjc.school.service.impl.AuthorService;
import com.mjc.school.service.view.AuthorView;

public class AuthorController {
    private AuthorService model;
    private AuthorView authorView;

    public AuthorController(){
        model = new AuthorService();
        authorView = new AuthorView();
    }

    public void showView(){
        authorView.display();
    }

    public void update(AuthorDto authorDto){
        model.update(authorDto);
    }

    public void create(AuthorDto authorDto) throws CharacterLengthIsNotValidException {
        model.create(authorDto);
    }


}

package com.mjc.school.controller;

import com.mjc.school.repository.exception.NotFoundException;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.impl.AuthorService;
import com.mjc.school.service.view.AuthorView;

public class AuthorController {
    private final AuthorService model;
    private final AuthorView authorView;

    public AuthorController(){
        model = AuthorService.getInstance();
        authorView = new AuthorView();
    }

    public void showView(){
        authorView.display();
    }

    public void update(AuthorDto authorDto){
        try {
            model.update(authorDto);
        }catch(NotFoundException e){
            e.printStackTrace();
        }
    }

    public void create(AuthorDto authorDto){
        model.create(authorDto);
    }


}

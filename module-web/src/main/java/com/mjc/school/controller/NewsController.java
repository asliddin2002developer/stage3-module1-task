package com.mjc.school.controller;

import com.mjc.school.service.view.NewsView;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsService;
import lombok.*;

@Getter
@Setter
public class NewsController {
    private NewsService model;
    private NewsView newsView;

    public NewsController(){
        model = NewsService.getInstance();
        newsView = new NewsView();
    }   
    public void showView(){
        newsView.display();
    }

    public void update(NewsDto news){
        model.update(news);
    }

    public void create(NewsDto news){
        model.create(news);
    }

}

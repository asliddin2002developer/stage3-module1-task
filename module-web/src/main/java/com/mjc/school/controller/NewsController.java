package com.mjc.school.controller;

import com.mjc.school.service.view.NewsView;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsService;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class NewsController {
    private NewsService model;
    private NewsView newsView;

    public NewsController(NewsService newsService){
        model = newsService;
        newsView = new NewsView(newsService);
    }

    public void getAllNews(){
        List<NewsDto> news = model.readAll();
        for(NewsDto n : news){
            System.out.println(n);
        }
    }

    public void getNewsById(Long id){
        System.out.println(model.readById(id));
    }

    public void showView(){
        newsView.display();
    }

    public void updateNews(NewsDto news){
        System.out.println(model.update(news));
    }

    public void createNews(NewsDto newsDto){
        System.out.println(model.create(newsDto));
    }

    public void deleteNews(Long id){
        System.out.println(model.delete(id));
    }

}

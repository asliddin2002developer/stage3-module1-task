package com.mjc.school.service.view;

import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsService;

import java.util.List;

public class NewsView {
    private List<NewsDto> allNews;
    NewsService newsService;

    public NewsView(){
        newsService = NewsService.getInstance();
        allNews = newsService.readAll();
    }
    public void display(){
        update();
        System.out.println(allNews);
    }

    public void update(){
        newsService = NewsService.getInstance();
        allNews = newsService.readAll();
    }
}

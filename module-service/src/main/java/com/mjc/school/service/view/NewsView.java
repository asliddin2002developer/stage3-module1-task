package com.mjc.school.service.view;

import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsService;

import java.util.List;

public class NewsView {
    private List<NewsDto> allNews;
    private List<AuthorDto> allAuthor;
    NewsService newsService = NewsService.getInstance();

    public NewsView(){
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

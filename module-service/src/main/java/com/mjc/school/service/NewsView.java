package com.mjc.school.service;

import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsService;

import java.util.List;

public class NewsView {
    List<NewsDto> allNews;
    List<AuthorDto> allAuthor;
    NewsService newsService = NewsService.getInstance();

    public NewsView(){
        allNews = newsService.readAll();
    }
    public void update(){
        newsService = NewsService.getInstance();
        allNews = newsService.readAll();
        System.out.println(allNews);

    }
}

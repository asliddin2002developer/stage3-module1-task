package com.mjc.school.repository.impl;

import com.mjc.school.Repository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.exception.NotFoundException;
import com.mjc.school.repository.model.NewsModel;

import java.time.LocalDateTime;
import java.util.List;
import java.lang.Boolean;
import lombok.*;

@Getter
@Setter
public class NewsRepository implements Repository<NewsModel> {
    private final DataSource dataSource;
    public NewsRepository() {
        dataSource = DataSource.getInstance();
    }


    @Override
    public NewsModel create(NewsModel news) {
        return dataSource.addNewsToDataSource(news);
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNewsDataSource();
    }

    @Override
    public NewsModel readById(Long id){
        System.out.println("Get news by id");
        try {
            return findNewsById(id);
        }catch(NotFoundException e){
            throw e;
        }
    }

    @Override
    public NewsModel update(NewsModel news) {
        try {
            NewsModel newsModel = findNewsById(news.getId());
            System.out.println("Update news");
            LocalDateTime date = LocalDateTime.now();
            newsModel.setTitle(news.getTitle());
            newsModel.setContent(news.getContent());
            newsModel.setLastUpdateDate(date);
            newsModel.setAuthorId(news.getAuthorId());
            return newsModel;
        }catch(NotFoundException e){
            throw e;
        }
    }

    @Override
    public Boolean delete(Long id) {
        System.out.println("Delete news with id");
        try {
            NewsModel newsModel = findNewsById(id);
            dataSource.getNewsDataSource().remove(newsModel);
            return Boolean.valueOf("true");
        }catch(NotFoundException e){
            throw e;
        }

    }

    public NewsModel findNewsById(Long id){
        for (NewsModel news : dataSource.getNewsDataSource()){
            if (news.getId().equals(id)){
                return news;
            }
        }
        throw new NotFoundException("News with given id NOT FOUND");
    }

}

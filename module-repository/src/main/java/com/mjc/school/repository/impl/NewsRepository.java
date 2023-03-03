package com.mjc.school.repository.impl;

import com.mjc.school.Repository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.exception.NewsNotFoundException;
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
    public NewsModel readById(Long id) {
        System.out.println("Get news by id");
        for (NewsModel news : dataSource.getNewsDataSource()){
            if (news.getId().equals(id)){
                System.out.println("news found");
                return news;
            }
        }
        //throw NewsNotFoundException
        System.out.println("Exception");
        throw new NewsNotFoundException("Couldn't find the News with provided id");
    }

    @Override
    public NewsModel update(NewsModel news) {
        System.out.println("Update news");
        for (NewsModel cur : dataSource.getNewsDataSource()){
            if (cur.getId().equals(news.getId())){
                LocalDateTime date = LocalDateTime.now();
                cur.setTitle(news.getTitle());
                cur.setContent(news.getContent());
                cur.setLastUpdateDate(date);
                cur.setAuthorId(news.getAuthorId());
            }
        }
        return news;
    }

    @Override
    public Boolean delete(Long id) {
        System.out.println("Delete news with id");
        for(NewsModel news : dataSource.getNewsDataSource()){
            if (news.getId().equals(id)){
                dataSource.getNewsDataSource().remove(news);
                return Boolean.valueOf("true");
            }
        }
        return Boolean.valueOf("false");
    }

    public NewsModel isNewsExist(Long id){
        for (NewsModel news : dataSource.getNewsDataSource()){
            if (news.getId().equals(id)){
                return news;
            }
        }
        throw new NewsNotFoundException("News Not Found");
    }
}

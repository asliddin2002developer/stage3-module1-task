package com.mjc.school.repository.impl;

import com.mjc.school.Repository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.exception.NewsNotFoundException;
import com.mjc.school.repository.model.NewsModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class NewsRepository implements Repository<NewsModel> {
    private DataSource dataSource = DataSource.getInstance();

    @Override
    public NewsModel create(NewsModel news) {
        return dataSource.addNewsToDataSource(news);
    }

    @Override
    public List<NewsModel> getAllNews() {
        return dataSource.getNewsDataSource();
    }

    @Override
    public NewsModel getNewsById(Long id) {
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
    public NewsModel update(Long id) {
        System.out.println("Update news");
        updateHelper(id);
        return dataSource.getNewsDataSource().stream()
                .filter(news -> news.getId().equals(id))
                .findFirst()
                .get();
    }

    private void updateHelper(Long id){
        System.out.println("Enter title of the news (length should be 5 to 30):");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        System.out.println("Enter content of the news (the length should be 5 to 255)");
        String content = scanner.nextLine();

        System.out.println("Enter the author id:");
        Long authorId = scanner.nextLong();

        LocalDateTime currentDate = LocalDateTime.now();

        dataSource.getNewsDataSource().stream()
                .filter(news -> news.getId().equals(id))
                .forEach(news -> {
                    news.setId(id);
                    news.setTitle(title);
                    news.setContent(content);
                    news.setAuthorId(authorId);
                    news.setLastUpdateDate(currentDate);
                });
    }

    @Override
    public boolean delete(Long id) {
        System.out.println("Delete news with id");
        for(NewsModel news : dataSource.getNewsDataSource()){
            if (news.getId().equals(id)){
                dataSource.getNewsDataSource().remove(news);
                return true;
            }
        }
        return false;
    }
}

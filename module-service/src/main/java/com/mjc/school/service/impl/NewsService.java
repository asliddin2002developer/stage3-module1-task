package com.mjc.school.service.impl;

import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validation.NewsValidation;

import java.util.List;
import java.util.stream.Collectors;

public class NewsService implements Service<NewsDto> {
    private final NewsRepository newsRepository;
    private final NewsValidation ERROR_VALIDATOR;
    private final NewsMapper newsMapper;

    public NewsService(DataSource dataSource){
        newsRepository = new NewsRepository(dataSource);
        ERROR_VALIDATOR = new NewsValidation(dataSource);
        newsMapper = new NewsMapper();
    }

    public NewsDto create(NewsDto newsDto) {
        //validate
        if (ERROR_VALIDATOR.isValidNewsParams(newsDto)) {
            NewsModel news = newsMapper.toModelCreate(newsDto);
            newsRepository.getDataSource().getNewsDataSource().add(news);
            System.out.println("NEWS WAS SUCCESSFULLY CREATED");
            return newsMapper.toDto(news);
        }
        return newsDto;

    }


    @Override
    public NewsDto update(NewsDto newsDto) {
        if (ERROR_VALIDATOR.isValidNewsParams(newsDto)) {
            NewsModel news = newsRepository.update(newsMapper.toModel(newsDto));
            System.out.println("UPDATE WAS SUCCESSFULL");
            return newsMapper.toDto(news);
        }
        return newsDto;
    }

    @Override
    public NewsDto readById(Long id) {
        NewsModel newsModel = newsRepository.findNewsById(id);
        return newsMapper.toDto(newsModel);
    }

    @Override
    public List<NewsDto> readAll() {
        List<NewsModel> newsList = newsRepository.readAll();
        return newsList.stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        return newsRepository.delete(id);
    }
}

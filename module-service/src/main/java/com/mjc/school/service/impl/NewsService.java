package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.NewsCreationDto;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.AuthorNotFoundException;
import com.mjc.school.service.mapper.Mapper;
import com.mjc.school.service.validation.NewsValidation;

import java.util.List;
import java.util.stream.Collectors;

public class NewsService implements Service<NewsDto> {
    private final NewsRepository newsRepository;
    private final NewsValidation ERROR_VALIDATOR;
    private final Mapper mapper;

    public NewsService() {
        this.newsRepository = new NewsRepository();
        ERROR_VALIDATOR = new NewsValidation();
        mapper = new Mapper();
    }

    @Override
    public NewsDto create(NewsDto newsDto) {
        //validate
        if (!ERROR_VALIDATOR.isValidNewsParams(newsDto)){
            throw new AuthorNotFoundException("Author not found with given id.");
        }
        NewsModel news = mapper.toNewsModel(new NewsCreationDto(newsDto.getTitle(),
                                                                newsDto.getContent(),
                                                                newsDto.getAuthorId()));
        newsRepository.getDataSource().getNewsDataSource().add(news);

        return newsDto;
    }

    @Override
    public NewsDto readById(Long id) {
        NewsModel news = newsRepository.isNewsExist(id);
        return mapper.toDto(news);

    }

    @Override
    public List<NewsDto> readAll() {
        List<NewsModel> newsList = newsRepository.readAll();
        return newsList.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto update(NewsDto newsDto) {
        NewsModel news = newsRepository.update(mapper.toNewsModel(new NewsCreationDto(newsDto.getTitle(),
                                                                     newsDto.getContent(),
                                                                         newsDto.getAuthorId())));
        return mapper.toDto(news);
    }

    @Override
    public Boolean delete(Long id) {
        return newsRepository.delete(id);
    }
}

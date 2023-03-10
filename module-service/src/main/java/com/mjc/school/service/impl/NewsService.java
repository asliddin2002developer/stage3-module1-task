package com.mjc.school.service.impl;

import com.mjc.school.repository.exception.NotFoundException;
import com.mjc.school.repository.exception.ValidatorException;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.NewsCreationDto;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validation.NewsValidation;

import java.util.List;
import java.util.stream.Collectors;

public class NewsService implements Service<NewsDto> {
    private NewsRepository newsRepository;
    private NewsValidation ERROR_VALIDATOR;
    private NewsMapper newsMapper;
    private static final Object OBJECT = new Object();
    private static volatile NewsService INSTANCE;

    public static NewsService getInstance(){
        NewsService result = INSTANCE;
        if (result == null){
            synchronized(OBJECT){
                result = new NewsService();
                result.newsRepository = new NewsRepository();
                result.ERROR_VALIDATOR = new NewsValidation();
                result.newsMapper = new NewsMapper();
                INSTANCE = result;

            }
        }
        return result;

    }



    @Override
    public NewsDto create(NewsDto newsDto) {
        //validate
            if (ERROR_VALIDATOR.isValidNewsParams(newsDto)) {
                NewsModel news = newsMapper.toModel(new NewsCreationDto(newsDto.getId(),
                        newsDto.getTitle(),
                        newsDto.getContent(),
                        newsDto.getAuthorId()));
                newsRepository.getDataSource().getNewsDataSource().add(news);
                return newsDto;
            }
            throw new ValidatorException("News Params is not valid");
        }


    @Override
    public NewsDto update(NewsDto newsDto) {
        if (ERROR_VALIDATOR.isValidNewsParams(newsDto)) {
            NewsModel news = newsRepository.update(newsMapper.toModel(new NewsCreationDto(
                    newsDto.getId(),
                    newsDto.getTitle(),
                    newsDto.getContent(),
                    newsDto.getAuthorId())));
            return newsMapper.toDto(news);
        }
        throw new ValidatorException("News Param is not valid");
    }

    @Override
    public NewsDto readById(Long id) {
        NewsModel news = newsRepository.isNewsExist(id);
        return newsMapper.toDto(news);

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
        if (newsRepository.delete(id)){
            return true;
        }else{
            throw new NotFoundException("News with given id NOT FOUND");
        }
    }
}

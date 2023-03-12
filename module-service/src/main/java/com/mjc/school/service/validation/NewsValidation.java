package com.mjc.school.service.validation;

import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.exception.NotFoundException;
import com.mjc.school.repository.exception.ValidatorException;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.service.dto.NewsDto;

public class NewsValidation {
    private final String TITLE_LENGTH_VALIDATOR;
    private final String CONTENT_LENGTH_VALIDATOR;
    private final String AUTHOR_NOT_FOUND_VALIDATOR;
    private final AuthorRepository authorRepository;

    public NewsValidation(DataSource dataSource){
        TITLE_LENGTH_VALIDATOR = "Title field should have length of value from 5 to 30";
        CONTENT_LENGTH_VALIDATOR = "Content field should have length of value from 5 to 255";
        AUTHOR_NOT_FOUND_VALIDATOR = "AuthorId should be mapped to the author datasource";
        authorRepository = new AuthorRepository(dataSource);
    }

    public boolean isValidNewsParams(NewsDto news){
        String title = news.getTitle();
        String content = news.getContent();
        Long authorId = news.getAuthorId();


        if (title.length() < 5 || news.getTitle().length() >= 30) {
            throw new ValidatorException(TITLE_LENGTH_VALIDATOR);
        } else if (content.length() < 5 || news.getContent().length() >= 255) {
            throw new ValidatorException(CONTENT_LENGTH_VALIDATOR);
        }else if (authorRepository.findAuthorById(authorId).getId() == null) {
            throw new NotFoundException(AUTHOR_NOT_FOUND_VALIDATOR);
        }
        return true;
    }

}

package com.mjc.school.service.validation;

import com.mjc.school.repository.exception.LengthIsNotValidException;
import com.mjc.school.repository.exception.NotFoundException;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.service.dto.NewsDto;

public class NewsValidation {
    private final String TITLE_LENGTH_VALIDATOR = "Title field should have length of value from 5 to 30";
    private final String CONTENT_LENGTH_VALIDATOR = "Content field should have length of value from 5 to 255";
    private final String AUTHOR_NOT_FOUND_VALIDATOR = "AuthorId should be mapped to the author datasource";
    private final AuthorRepository authorRepository = new AuthorRepository();
    //logic

    public boolean isValidNewsParams(NewsDto news){
        String title = news.getTitle();
        String content = news.getContent();
        Long authorId = news.getAuthorId();


        if (title.length() < 5 || news.getTitle().length() >= 30) {
            System.err.println(TITLE_LENGTH_VALIDATOR);
            return false;
        } else if (content.length() < 5 || news.getContent().length() >= 255) {
            System.err.println(CONTENT_LENGTH_VALIDATOR);
            return false;
        } else if (authorRepository.isAuthorExist(authorId) == null) {
            System.err.println(AUTHOR_NOT_FOUND_VALIDATOR);
            return false;
        }
        return true;
    }
}

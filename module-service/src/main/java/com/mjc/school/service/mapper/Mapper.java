package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsCreationDto;
import com.mjc.school.service.dto.NewsDto;

public class Mapper {
    public NewsDto toDto(NewsModel news){
        String title = news.getTitle();
        String content = news.getContent();
        Long authorId = news.getAuthorId();
        return new NewsDto(title, content, authorId);
    }

    public NewsModel toNewsModel(NewsCreationDto newsCreationDto){
        return new NewsModel(
                newsCreationDto.getTitle(),
                newsCreationDto.getContent(),
                newsCreationDto.getAuthorId()
        );
    }
}

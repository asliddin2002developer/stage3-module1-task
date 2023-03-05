package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsCreationDto;
import com.mjc.school.service.dto.NewsDto;

public class NewsMapper {
    public NewsDto toDto(NewsModel news){
        Long id = news.getId();
        String title = news.getTitle();
        String content = news.getContent();
        Long authorId = news.getAuthorId();
        return new NewsDto(id,title, content, authorId);
    }

    public NewsModel toModel(NewsCreationDto newsCreationDto){
        return new NewsModel(
                newsCreationDto.getId(),
                newsCreationDto.getTitle(),
                newsCreationDto.getContent(),
                newsCreationDto.getAuthorId()
        );
    }
}

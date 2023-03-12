package com.mjc.school.service.dto;

import lombok.*;
@Getter
@Setter
public class NewsDto {
    private Long id;
    private String title;
    private String content;
    private Long authorId;

    public NewsDto(String title, String content, Long authorId){
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    public NewsDto(Long id, String title, String content, Long authorId){
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }
    public String toString(){
        return "[" +
                    id + "," +
                    title + "," +
                    content + "," +
                    authorId  +
                "]";

    }

}

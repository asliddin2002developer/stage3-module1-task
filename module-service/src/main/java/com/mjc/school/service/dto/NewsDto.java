package com.mjc.school.service.dto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
public class NewsDto {
    private Long id;
    private String title;
    private String content;
    private Long authorId;

    public String toString(){
        return "[" +
                    title + "," +
                    content + "," +
                    authorId  +
                "]";

    }

}

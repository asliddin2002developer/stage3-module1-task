package com.mjc.school.service.dto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
public class NewsDto {
    private String title;
    private String content;
    private Long authorId;

}

package com.mjc.school.repository.dto;

import java.time.LocalDateTime;

public class NewsCreationDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

}

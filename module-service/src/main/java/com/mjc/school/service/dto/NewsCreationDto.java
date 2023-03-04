package com.mjc.school.service.dto;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class NewsCreationDto {
    private Long id;
    private String title;
    private String content;
    private Long authorId;

}

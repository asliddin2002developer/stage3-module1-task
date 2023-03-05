package com.mjc.school.service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class AuthorDto {
    private Long id;
    private String name;

    public String toString(){
        return "["  +
                    id + "," +
                    name +
                "]";
    }

}

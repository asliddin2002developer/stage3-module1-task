package com.mjc.school.repository.model;
import java.util.concurrent.atomic.AtomicLong;
import lombok.*;

@Getter
@Setter
public class AuthorModel {
    private static final AtomicLong count = new AtomicLong(0);
    private Long id;
    private String name;

    public AuthorModel(String name){
        this.id = count.incrementAndGet();
        this.name = name;
    }

    public AuthorModel(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public String toString(){
        return "[" +
                    id + "," +
                    name +
                "]";

    }

}

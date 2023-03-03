package com.mjc.school.repository.model;
import java.util.concurrent.atomic.AtomicLong;

public class AuthorModel {
    private static final AtomicLong count = new AtomicLong(0);
    private Long id;
    private String name;

    public AuthorModel(String name){
        this.id = count.incrementAndGet();
        this.name = name;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}

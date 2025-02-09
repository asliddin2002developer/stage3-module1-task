package com.mjc.school.repository.model;
import lombok.*;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;


@Getter
@Setter
public class NewsModel {
    private static final AtomicLong count = new AtomicLong(0);
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

    public NewsModel(String title, String content, Long authorId){
        this.id = count.incrementAndGet();
        this.title = title;
        this.content = content;
        this.createDate =  LocalDateTime.now();;
        this.lastUpdateDate = LocalDateTime.now();
        this.authorId = authorId;
    }

    public NewsModel(Long id, String title, String content, Long authorId){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate =  LocalDateTime.now();;
        this.lastUpdateDate = LocalDateTime.now();
        this.authorId = authorId;
    }


    public String toString(){
        return "[" + this.getId() + "," +
                       this.getContent() + "," +
                       this.getAuthorId() + "," +
                       this.getCreateDate() + "," +
                       this.getLastUpdateDate() +
                "]";

    }
}

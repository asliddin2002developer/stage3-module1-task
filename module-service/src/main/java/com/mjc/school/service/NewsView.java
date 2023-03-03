package com.mjc.school.service;

public class NewsView {
    public void printNewsDetails(String title, String content, Long authorId){
        System.out.println("Title: " + title);
        System.out.println("Content: " + content);
        System.out.println("AuthorId: " + authorId);
    }

    public void printAuthorDetails(String name){
        System.out.println("Author: " + name);
    }
}

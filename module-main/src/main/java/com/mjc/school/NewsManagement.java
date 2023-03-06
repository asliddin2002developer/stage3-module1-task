package com.mjc.school;


import com.mjc.school.controller.AuthorController;
import com.mjc.school.controller.NewsController;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.AuthorNotFoundException;
import com.mjc.school.service.exception.CharacterLengthIsNotValidException;
import com.mjc.school.service.impl.NewsService;

public class NewsManagement {


    public static void main(String[] args) throws AuthorNotFoundException, CharacterLengthIsNotValidException {
        NewsManagement.init();
    }

    static DataSource database;
    public static void init() throws AuthorNotFoundException, CharacterLengthIsNotValidException {
        NewsManagement newsManagement = new NewsManagement();
        database = newsManagement.retrieveDatabase("content.txt", "author.txt");
//        System.out.println(database.getNewsDataSource());
//        System.out.println(database.getAuthorsDataSource());

        start();
    }

    public static void start() throws AuthorNotFoundException, CharacterLengthIsNotValidException {
//        NewsController controller = new NewsController();
//        System.out.println("Before update");
//        controller.showView();
//
//        NewsService news = new NewsService();
//
//        NewsDto dto = news.readById(1L);
//        dto.setTitle("Updated");
//        dto.setContent("Updated");
//        controller.update(dto);
//
////        controller.create(new NewsDto(3L, "New News", "Be careful, it is new", 1L ));
//        System.out.println("After update");
//        controller.getModel().readById(1L);
//        controller.showView();

        AuthorController controller = new AuthorController();
        controller.showView();
        AuthorDto author = new AuthorDto(1L, "Ke");
//        controller.update(author);
//        System.out.println("after");
//        controller.showView();

        controller.create(author);
    }

    public DataSource retrieveDatabase(String newsFile, String authorFile){
        DataSource database = DataSource.getInstance();
        database.readFile(newsFile, authorFile);
        return database;
    }
}

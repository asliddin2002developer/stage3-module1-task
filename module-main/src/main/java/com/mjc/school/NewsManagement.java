package com.mjc.school;


import com.mjc.school.controller.NewsController;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsService;
import com.mjc.school.service.mapper.Mapper;

public class NewsManagement {

    static DataSource database;
    public static void init(){
        NewsManagement newsManagement = new NewsManagement();
        database = newsManagement.retrieveDatabase("content.txt", "author.txt");
//        System.out.println(database.getNewsDataSource());
//        System.out.println(database.getAuthorsDataSource());

        start();
    }

    public static void start(){
        NewsController controller = new NewsController();
        System.out.println("Before update");
        controller.showView();

        NewsService news = new NewsService();

        NewsDto dto = news.readById(1L);
        dto.setTitle("Updated");
        dto.setContent("Updated");
        controller.update(dto);

//        controller.create(new NewsDto(3L, "New News", "Be careful, it is new", 1L ));
        System.out.println("After update");
        controller.getModel().readById(1L);
        controller.showView();
    }

    public DataSource retrieveDatabase(String newsFile, String authorFile){
        DataSource database = DataSource.getInstance();
        database.readFile(newsFile, authorFile);
        return database;
    }
}

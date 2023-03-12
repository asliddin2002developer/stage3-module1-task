package com.mjc.school;


import com.mjc.school.controller.NewsController;
import com.mjc.school.repository.datasource.DataSource;

import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsService;

import java.util.Scanner;

public class NewsManagement {
    private Scanner scanner;
    private NewsController controller;

    public NewsManagement(NewsService newsService){
        controller = new NewsController(newsService);
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args)  {
        DataSource database = retrieveDatabase("content.txt", "author.txt");
        NewsService newsService = new NewsService(database);
        NewsManagement newsManagement = new NewsManagement(newsService);
        newsManagement.init();
    }
    public void init(){

        while (true) {
            menu();
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    //get all news
                    System.out.println("================================ ALL NEWS =======================");
                    controller.getAllNews();
                    System.out.println("=================================================================");
                    break;
                case "2":
                    //get news by id
                    System.out.println("Enter news ID:");
                    System.out.println("================================ NEWS BY ID =======================");
                    controller.getNewsById(Long.valueOf(scanner.nextLine()));
                    System.out.println("===================================================================");
                    break;
                case "3":
                    //update news
                    controller.updateNews(menuHelper());
                    break;
                case "4":
                    //Create news
                    controller.createNews(createHelper());
                    break;
                case "5":
                    //delete news
                    System.out.println("Enter news ID:");
                    controller.deleteNews(Long.valueOf(scanner.nextLine()));
                    break;
                default:
                    System.err.println("Enter proper command!");
            }
        }
    }


    public NewsDto createHelper(){
        System.out.println("Enter News TITLE:");
        String title = scanner.nextLine();
        System.out.println("Enter News CONTENT:");
        String content = scanner.nextLine();
        System.out.println("Enter author ID:");
        Long authorId = Long.valueOf(scanner.nextLine());

        return new NewsDto(title, content, authorId);
    }
    public NewsDto menuHelper(){
        long id = 0;
        System.out.println("Enter News ID: ");
        try{
            id = Long.parseLong(scanner.nextLine());
        }catch(RuntimeException e){
            throw new NumberFormatException();
        }
        System.out.println("Enter News TITLE:");
        String title = scanner.nextLine();
        System.out.println("Enter News CONTENT:");
        String content = scanner.nextLine();
        System.out.println("Enter author ID:");
        Long authorId = Long.valueOf(scanner.nextLine());

        return new NewsDto(id, title, content, authorId);
    }
    

    public void menu(){
        System.out.println("Welcome to the News");
        System.out.println("Get All News - 1");
        System.out.println("Get News By Id - 2");
        System.out.println("Update News - 3");
        System.out.println("Create News - 4");
        System.out.println("Delete News - 5");
    }

    public static DataSource retrieveDatabase(String newsFile, String authorFile){
        DataSource database = new DataSource();
        database.readFile(newsFile, authorFile);
        return database;
    }
}

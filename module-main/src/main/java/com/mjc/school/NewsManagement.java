package com.mjc.school;


import com.mjc.school.controller.NewsController;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.exception.NotFoundException;
import com.mjc.school.repository.exception.ValidatorException;
import com.mjc.school.service.dto.NewsDto;

import java.util.Scanner;

public class NewsManagement {

    private DataSource database;
    private Scanner scanner;
    private NewsController controller;

    public NewsManagement(){
        controller = new NewsController();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args)  {
        NewsManagement newsManagement = new NewsManagement();
        newsManagement.init();
    }
    public void init(){
        database = retrieveDatabase("content.txt", "author.txt");

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
                    try {
                        controller.updateNews(menuHelper());
                        System.out.println("UPDATE WAS SUCCESSFULL");
                    }catch(ValidatorException e){
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    //Create news
                    try {
                        controller.createNews(menuHelper());
                        System.out.println("NEWS WAS SUCCESSFULLY CREATED");
                    }catch(ValidatorException e ){
                        e.printStackTrace();
                    }catch(RuntimeException e){
                        e.printStackTrace();
                    }
                    break;
                case "5":
                    //delete news
                    System.out.println("Enter news ID:");
                    try {
                        controller.deleteNews(Long.valueOf(scanner.nextLine()));
                    }catch(NotFoundException e){
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.err.println("Enter proper command!");
            }
        }
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

    public DataSource retrieveDatabase(String newsFile, String authorFile){
        DataSource database = DataSource.getInstance();
        database.readFile(newsFile, authorFile);
        return database;
    }
}

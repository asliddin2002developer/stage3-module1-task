package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
public class DataSource {
    private List<NewsModel> newsDataSource;
    private List<AuthorModel> authorsDataSource;
    private static final Object OBJECT = new Object();
    private static volatile DataSource INSTANCE;

    public static DataSource getInstance(){
        DataSource result = INSTANCE;
        if (result == null){
            synchronized(OBJECT){
                result = new DataSource();
                result.newsDataSource = new ArrayList<>();
                result.authorsDataSource = new ArrayList<>();
                INSTANCE = result;

            }
        }
        return result;
    }

    public void readFile(String newsFile, String authorsFile){

        DataSource app = DataSource.getInstance();
        InputStream newsStream = app.getFileFromResourceAsStream(newsFile);
        InputStream authorStream = app.getFileFromResourceAsStream(authorsFile);

        readAllNews(newsStream);
        readAllAuthors(authorStream);

    }

    public InputStream getFileFromResourceAsStream(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null){
            throw new IllegalArgumentException("File not found " + fileName);
        }else{
            return inputStream;
        }
    }

    public void readAllNews(InputStream is){
        try (
                InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)
        )
        {
            String line;
            while ((line = reader.readLine()) != null){
                String[] arr = line.split(",");
                String title = arr[0].split(":")[0].strip();
                String content = arr[1].split(":")[1].strip();
                String authorId = arr[4].split(":")[1].strip();
                newsDataSource.add(new NewsModel(title, content, Long.valueOf(authorId)));
            }
//            System.out.println(newsDataSource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readAllAuthors(InputStream is){
        try (
                InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)
        )
        {
            String line;
            while ((line = reader.readLine()) != null){
                String[] arr = line.split(",");
                Long authorId = Long.valueOf(arr[0].strip());
                String authorName = arr[1].strip();
                authorsDataSource.add(new AuthorModel(authorId, authorName));
            }
//            System.out.println(newsDataSource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<NewsModel> getNewsDataSource(){
        return newsDataSource;
    }
    public List<AuthorModel> getAuthorsDataSource(){
        return authorsDataSource;
    }

    public NewsModel addNewsToDataSource(NewsModel news){
        newsDataSource.add(news);
        return news;
    }

    public AuthorModel addAuthorToDataSource(AuthorModel author){
        authorsDataSource.add(author);
        return author;
    }



}

package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.NewsModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    String fileName;
    private List<NewsModel> newsDataSource = new ArrayList<>();
    private static Object OBJECT = new Object();
    private static volatile DataSource INSTANCE;

    public static DataSource getInstance(){
        DataSource result = INSTANCE;
        if (result == null){
            synchronized(OBJECT){
                if (result == null){
                    result = new DataSource();
                    INSTANCE = result;
                }
            }
        }
        return result;
    }

    public void readFile(String fileName){

        DataSource app = DataSource.getInstance();

        this.fileName = fileName;

        InputStream is = app.getFileFromResourceAsStream(fileName);
        readDataFromFile(is);

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

    public void readDataFromFile(InputStream is){
        try (
                InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)
        )
        {
            String line;
            while ((line = reader.readLine()) != null){
                String[] arr = line.split(",");
                String title = arr[1].split(":")[1].strip();
                String content = arr[2].split(":")[1].strip();
                String authorId = arr[5].split(":")[1].strip();
                newsDataSource.add(new NewsModel(title, content, Long.valueOf(authorId)));
            }
            System.out.println(newsDataSource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<NewsModel> getNewsDataSource(){
        return newsDataSource;
    }

    public NewsModel addNewsToDataSource(NewsModel news){
        newsDataSource.add(news);
        return news;
    }



}

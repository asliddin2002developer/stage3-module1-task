package com.mjc.school.repository.impl;

import com.mjc.school.Repository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.exception.NewsNotFoundException;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

import java.time.LocalDateTime;
import java.util.List;

public class AuthorRepository implements Repository<AuthorModel> {
        private final DataSource dataSource;
        public AuthorRepository() {
            dataSource = DataSource.getInstance();
        }


    @Override
        public AuthorModel create(AuthorModel author) {
            return dataSource.addAuthorToDataSource(author);
        }

        @Override
        public List<AuthorModel> readAll() {
            return dataSource.getAuthorsDataSource();
        }

        @Override
        public AuthorModel readById(Long id) {
            System.out.println("Get author by id");
            for (AuthorModel author : dataSource.getAuthorsDataSource()){
                if (author.getId().equals(id)){
                    System.out.println("news found");
                    return author;
                }
            }
            //throw NewsNotFoundException
            System.out.println("Exception");
            throw new NewsNotFoundException("Couldn't find the News with provided id");
        }

        @Override
        public AuthorModel update(AuthorModel author) {
            System.out.println("Update author");
            for (AuthorModel cur : dataSource.getAuthorsDataSource()){
                if (cur.getId().equals(author.getId())){
                    cur.setName(author.getName());
                }
            }
            return author;
        }

        @Override
        public Boolean delete(Long id) {
            System.out.println("Delete news with id");
            for(AuthorModel author : dataSource.getAuthorsDataSource()){
                if (author.getId().equals(id)){
                    dataSource.getNewsDataSource().remove(author);
                    return Boolean.valueOf("true");
                }
            }
            return Boolean.valueOf("false");
        }

        public boolean isAuthorExist(Long id){
            for (AuthorModel author : dataSource.getAuthorsDataSource()){
                if (author.getId() == id){
                    return true;
                }
            }
            return false;
        }
}



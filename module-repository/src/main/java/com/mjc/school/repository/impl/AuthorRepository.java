package com.mjc.school.repository.impl;

import com.mjc.school.Repository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.exception.NotFoundException;
import com.mjc.school.repository.model.AuthorModel;
import lombok.*;
import java.util.List;

@Getter
@Setter
public class AuthorRepository implements Repository<AuthorModel> {
        private DataSource dataSource;
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
            try {
                AuthorModel authorModel = findAuthorById(id);
                System.out.println("author found");
                return authorModel;
            }catch(NotFoundException e){
                throw e;
            }
        }

        @Override
        public AuthorModel update(AuthorModel author) {
            try{
                AuthorModel authorModel = findAuthorById(author.getId());
                authorModel.setName(author.getName());
                return authorModel;
            }catch(NotFoundException e){
                throw e;
            }
        }

        @Override
        public Boolean delete(Long id) {
            System.out.println("Delete Author with id");
            try{
                AuthorModel author = findAuthorById(id);
                dataSource.getAuthorsDataSource().remove(author);
                return Boolean.valueOf("true");
            }catch(NotFoundException e){
                throw e;
            }

        }

        public AuthorModel findAuthorById(Long id) {
            for (AuthorModel author : dataSource.getAuthorsDataSource()) {
                if (author.getId().equals(id)) {
                    return author;
                }
            }
            throw new NotFoundException("Author with given id NOT FOUND");
        }

}



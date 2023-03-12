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
        public AuthorRepository(DataSource dataSource) {
            this.dataSource = dataSource;
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
            AuthorModel authorModel = findAuthorById(id);
            System.out.println("author found");
            return authorModel;
        }

        @Override
        public AuthorModel update(AuthorModel author) {
            AuthorModel authorModel = findAuthorById(author.getId());
            authorModel.setName(author.getName());
            return authorModel;
        }

        @Override
        public Boolean delete(Long id) {
            System.out.println("Delete Author with id");
            AuthorModel author = findAuthorById(id);
            dataSource.getAuthorsDataSource().remove(author);
            return Boolean.valueOf("true");

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



package com.mjc.school.service.impl;

import com.mjc.school.repository.exception.ValidatorException;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validation.AuthorValidation;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorService implements Service<AuthorDto> {
        private AuthorRepository authorRepository;
        private AuthorValidation errorValidator;
        private AuthorMapper authorMapper;
        private static final Object OBJECT = new Object();
        private static volatile AuthorService INSTANCE;

        public static AuthorService getInstance(){
            AuthorService result = INSTANCE;
            if (result == null){
                synchronized(OBJECT){
                    result = new AuthorService();
                    result.authorRepository = new AuthorRepository();
                    result.errorValidator = new AuthorValidation();
                    result.authorMapper = new AuthorMapper();
                    INSTANCE = result;

                }
            }
            return result;

        }



        @Override
        public AuthorDto create(AuthorDto authorDto){
            //validate
            if (errorValidator.isValidAuthorParams(authorDto)) {
                AuthorModel author = authorMapper.toModel(new AuthorDto(
                        authorDto.getId(),
                        authorDto.getName()
                ));
                authorRepository.getDataSource().getAuthorsDataSource().add(author);

                return authorDto;
            }
            throw new ValidatorException("Author length should be 3-15 characters");

        }

        @Override
        public AuthorDto readById(Long id) {
            AuthorModel author = authorRepository.isAuthorExist(id);
            return authorMapper.toDto(author);

        }

        @Override
        public List<AuthorDto> readAll() {
            List<AuthorModel> authorList = authorRepository.readAll();
            return authorList.stream()
                    .map(authorMapper::toDto)
                    .collect(Collectors.toList());
        }

        @Override
        public AuthorDto update(AuthorDto authorDto) {
            AuthorModel author = authorRepository.update(authorMapper.toModel(new AuthorDto(
                                                                authorDto.getId(),
                                                                authorDto.getName())));
            return authorMapper.toDto(author);
        }

        @Override
        public Boolean delete(Long id) {
            return authorRepository.delete(id);
        }
}

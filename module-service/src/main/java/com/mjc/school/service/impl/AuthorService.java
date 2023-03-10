package com.mjc.school.service.impl;

import com.mjc.school.repository.exception.NotFoundException;
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
        private final AuthorRepository authorRepository;
        private final AuthorValidation errorValidator;
        private final AuthorMapper authorMapper;
        private static AuthorService INSTANCE;

        private AuthorService(){
            authorRepository = new AuthorRepository();
            errorValidator = new AuthorValidation();
            authorMapper = new AuthorMapper();
        }
        public static AuthorService getInstance(){
            if (INSTANCE == null){
                INSTANCE = new AuthorService();
            }
            return INSTANCE;

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
            try {
                AuthorModel author = authorRepository.findAuthorById(id);
                return authorMapper.toDto(author);
            }catch(NotFoundException e){
                throw e;
            }

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
            try {
                AuthorModel author = authorRepository.update(authorMapper.toModel(new AuthorDto(
                        authorDto.getId(),
                        authorDto.getName())));
                return authorMapper.toDto(author);
            }catch(NotFoundException e){
                throw e;
            }
        }

        @Override
        public Boolean delete(Long id) {
            return authorRepository.delete(id);
        }
}

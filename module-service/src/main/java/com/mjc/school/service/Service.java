package com.mjc.school.service;

import com.mjc.school.service.exception.AuthorNotFoundException;
import com.mjc.school.service.exception.CharacterLengthIsNotValidException;

import java.util.List;
import java.lang.Boolean;

public interface Service<T> {
    T create(T t) throws AuthorNotFoundException, CharacterLengthIsNotValidException;
    T readById(Long id);
    List<T> readAll();
    T update(T t);
    Boolean delete(Long id);
}

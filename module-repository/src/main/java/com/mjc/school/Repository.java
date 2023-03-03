package com.mjc.school;

import java.util.List;

public interface Repository<T> {

        T create(T t);
        List<T> getAllNews();
        T getNewsById(Long id);
        T update(Long id);
        boolean delete(Long id);
}


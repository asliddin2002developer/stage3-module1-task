package com.mjc.school;

import java.util.List;
import java.lang.Boolean;

public interface Repository<T> {

        T create(T t);
        T readById(Long id);
        List<T> readAll();
        T update(T t);
        Boolean delete(Long id);
}


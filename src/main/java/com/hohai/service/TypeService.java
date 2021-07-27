package com.hohai.service;

import com.hohai.entity.Type;

import java.util.List;

public interface TypeService {
    List<Type> getAllType();

    Type getTypeByName(String name);

    int addType(Type type);

    void deleteType(Long id);

    Type getTypeById(Long id);

    void update(Type type);

    List<Type> getAllTypeAndBlog();

}

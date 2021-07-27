package com.hohai.dao;

import com.hohai.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/22 12:30 下午
 */
@Repository
public interface TypeDao {
    List<Type> getAllType();

    Type getTypeByName(String name);

    int addType(Type type);

    void deleteType(Long id);

    Type getTypeById(Long id);

    void update(Type type);

    List<Type> getAllTypeAndBlog();
}

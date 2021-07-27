package com.hohai.service.impl;

import com.hohai.dao.TypeDao;
import com.hohai.entity.Type;
import com.hohai.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/22 12:28 下午
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public int addType(Type type) {
        return typeDao.addType(type);
    }

    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeDao.getTypeById(id);
    }

    @Override
    public void update(Type type) {
        typeDao.update(type);
    }

    @Override
    public List<Type> getAllTypeAndBlog() {
        return typeDao.getAllTypeAndBlog();
    }

}

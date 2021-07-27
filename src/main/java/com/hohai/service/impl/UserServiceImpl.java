package com.hohai.service.impl;

import com.hohai.dao.UserDao;
import com.hohai.entity.User;
import com.hohai.service.UserService;
import com.hohai.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jin
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
    }
}

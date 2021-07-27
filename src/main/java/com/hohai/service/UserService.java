package com.hohai.service;

import com.hohai.entity.User;

public interface UserService {

    User findByUsernameAndPassword(String username, String password);
}

package com.hohai.dao;

import com.hohai.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jin
 */
@Repository
public interface UserDao {
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}

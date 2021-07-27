package com.hohai.service;

import com.hohai.entity.Friend;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/26 9:04 上午
 */
public interface FriendLinkService {
    List<Friend> getAll();

    int save(Friend friend);

    void deleteByID(Long id);

    Friend findById(Long id);

    int update(Friend friend);
}

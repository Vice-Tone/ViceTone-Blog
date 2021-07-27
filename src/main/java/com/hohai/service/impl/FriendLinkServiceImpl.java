package com.hohai.service.impl;

import com.hohai.dao.FriendLinkDao;
import com.hohai.entity.Friend;
import com.hohai.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/26 9:04 上午
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkDao friendLinkDao;

    @Override
    public List<Friend> getAll() {
        return friendLinkDao.getAll();
    }

    @Override
    public int save(Friend friend) {
        return friendLinkDao.save(friend);
    }

    @Override
    public void deleteByID(Long id) {
        friendLinkDao.deleteByID(id);
    }

    @Override
    public Friend findById(Long id) {
        return friendLinkDao.findById(id);
    }

    @Override
    public int update(Friend friend) {
        return friendLinkDao.update(friend);
    }
}

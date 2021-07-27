package com.hohai.dao;

import com.hohai.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/10/6 8:16 下午
 */
@Repository
public interface MessageDao {
    void saveMessage(Message message);

    List<Message> findParentMessage(long id);

    List<Message> findChildMessage(Long parentId);

    List<Message> findAfterChildMessage(Long childId);

    void deleteMessage(Long id);
}

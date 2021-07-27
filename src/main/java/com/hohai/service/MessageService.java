package com.hohai.service;

import com.hohai.entity.Message;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/10/6 8:15 下午
 */
public interface MessageService {
    void saveMessage(Message message);

    List<Message> listMessage();

    void deleteMessage(Long id);
}

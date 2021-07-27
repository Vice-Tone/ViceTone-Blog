package com.hohai.service.impl;

import com.hohai.dao.MessageDao;
import com.hohai.entity.Message;
import com.hohai.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/10/6 8:16 下午
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;


    private List<Message> tmpMessages = new ArrayList<>();

    /**
     * 新增留言
     * @param message
     */
    @Override
    public void saveMessage(Message message) {
        message.setCreateTime(new Date());
        messageDao.saveMessage(message);
    }

    /**
     * 留言功能同评论
     * @return
     */
    @Override
    public List<Message> listMessage() {
        List<Message> messages = messageDao.findParentMessage(Long.parseLong("-1"));
        for (Message message : messages) {
            Long parentId = message.getId();
            String nickname1 = message.getNickname();
            List<Message> childMessages = messageDao.findChildMessage(parentId);

            combineChildren(childMessages ,nickname1);

            message.setReplyMessages(tmpMessages);
            tmpMessages = new ArrayList<>();
        }
        return messages;
    }

    @Override
    public void deleteMessage(Long id) {
        messageDao.deleteMessage(id);
    }

    private void combineChildren(List<Message> childMessages ,String parentNickname) {
        if (childMessages.size() > 0) {
            for (Message childMessage : childMessages) {
                Long childId = childMessage.getId();
                String nickname = childMessage.getNickname();
                childMessage.setParentNickname(parentNickname);

//                childMessage.setParentMessageId(parentId);

                tmpMessages.add(childMessage);
                recurse(childId,nickname);
            }
        }
    }

    private void recurse(Long childId ,String nickname) {
        List<Message> afterChildMessages = messageDao.findAfterChildMessage(childId);
        if (afterChildMessages.size() > 0) {
            for (Message afterChildMessage : afterChildMessages) {
                Long id = afterChildMessage.getId();
                String nickname2 = afterChildMessage.getNickname();
//                afterChildMessage.setParentMessageId(childId);
                afterChildMessage.setParentNickname(nickname);
                tmpMessages.add(afterChildMessage);
                recurse(id,nickname2);
            }
        }
    }
}

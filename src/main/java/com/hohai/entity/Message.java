package com.hohai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/22 9:36 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Message {
    //主键
    private Long id;
    //留言昵称
    private String nickname;
    //留言邮箱
    private String email;
    //留言内容
    private String content;
    //留言头像
    private String avatar;
    //留言创建时间
    private Date createTime;
    //父留言id
    private Long parentMessageId;
    //是否为管理员留言
    private boolean adminMessage;

    //回复留言
    private List<Message> replyMessages = new ArrayList<>();
    //父留言
    private Message parentMessage;
    //父留言昵称
    private String parentNickname;
}

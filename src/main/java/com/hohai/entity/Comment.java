package com.hohai.entity;

import com.hohai.queryvo.DetailedBlog;
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
public class Comment {
    //主键
    private Long id;
    //昵称
    private String nickname;
    //邮箱
    private String email;
    //头像
    private String avatar;
    //评论内容
    private String content;
    //创建时间
    private Date createTime;
    //博客id
    private Long blogId;
    //父评论id
    private Long parentCommentId;
    //是否为管理员评论
    private boolean adminComment;

    //回复
    //父评论
    private Comment parentComment;
    //父评论昵称
    private String parentNickname;
    //回复集合
    private List<Comment> replyComments = new ArrayList<>();

    private DetailedBlog detailedBlog;
}

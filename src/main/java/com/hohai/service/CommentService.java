package com.hohai.service;

import com.hohai.entity.Comment;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/10/4 7:29 下午
 */
public interface CommentService {
    List<Comment> getListCommentByBlogId(Long blogId);

    void saveComment(Comment comment);

    void deleteComment(Comment comment, Long id);
}

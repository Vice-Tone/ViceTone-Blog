package com.hohai.dao;

import com.hohai.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/10/4 7:30 下午
 */
@Repository
public interface CommentDao {

    void saveComment(Comment comment);

    void deleteComment(Long id);

    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId,@Param("blogParentId") Long blogParentId);

    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId, @Param("id") Long id);

    List<Comment> findByBlogIdAndChildId(@Param("blogId") Long blogId,@Param("childId") Long childId);
}

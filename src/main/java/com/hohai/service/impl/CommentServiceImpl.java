package com.hohai.service.impl;

import com.hohai.dao.BlogDao;
import com.hohai.dao.CommentDao;
import com.hohai.entity.Comment;
import com.hohai.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/10/4 7:29 下午
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;

    private List<Comment> tmpReplys = new ArrayList<>();

    /**
     * 查询该博客的所有评论,同时要显示评论与回复效果
     *
     * @param blogId
     * @return
     */
    @Override
    public List<Comment> getListCommentByBlogId(Long blogId) {
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        for (Comment comment : comments) {
            Long id = comment.getId();
            String parentNickname1 = comment.getNickname();
            //根据博客id和父评论id查询出回复
            List<Comment> childComments = commentDao.findByBlogIdParentIdNotNull(blogId, id);
            //递归查询出所有评论回复，将所有回复放入一个集合中
            combineChildren(blogId, childComments, parentNickname1);
            comment.setReplyComments(tmpReplys);
//            for (Comment tmp : tmpReplys) {
//                System.out.println(tmp);
//            }
            tmpReplys = new ArrayList<>();
        }

        return comments;
    }

    /**
     * 为子评论设置相关属性值，并进行递归查询子评论
     *
     * @param blogId
     * @param childComments
     * @param parentNickname1
     */
    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
        if (childComments.size() > 0) {
            for (Comment childComment : childComments) {
                Long childId = childComment.getId();
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tmpReplys.add(childComment);
                recurse(blogId, childId, parentNickname);
            }
        }
    }

    private void recurse(Long blogId, Long childId, String parentNickname1) {
        List<Comment> replyComments = commentDao.findByBlogIdAndChildId(blogId, childId);
        if (replyComments.size() > 0) {
            for (Comment replyComment : replyComments) {
                Long id = replyComment.getId();
                String parentNickname = replyComment.getNickname();
                replyComment.setParentNickname(parentNickname1);
                tmpReplys.add(replyComment);
                recurse(blogId, id, parentNickname);
            }
        }
    }


    /**
     * 新增一条评论
     *
     * @param comment
     */
    @Override
    public void saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        commentDao.saveComment(comment);
        blogDao.updateCommentCount(comment.getBlogId());
    }

    /**
     * 删除一条评论
     *
     * @param comment
     * @param id
     */
    @Override
    public void deleteComment(Comment comment, Long id) {
        commentDao.deleteComment(id);
        blogDao.updateCommentCount(comment.getBlogId());
    }
}

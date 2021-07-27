package com.hohai.controller;

import com.hohai.entity.Comment;
import com.hohai.entity.User;
import com.hohai.queryvo.DetailedBlog;
import com.hohai.service.BlogService;
import com.hohai.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/10/4 7:29 下午
 */
@Controller
public class CommentController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Value("${comment.avatar}")
    private String avatar;


    /**
     * 新增一条评论，并更新博客里的评论数
     * @return
     */
    @PostMapping("/comments")
    public String saveComment(Comment comment, Model model, HttpSession session){
        Long blogId = comment.getBlogId();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }
        if (comment.getParentComment().getId() != null) {
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        commentService.saveComment(comment);
        List<Comment> comments = commentService.getListCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    /**
     * 根据id删除评论，并更新博客里的评论数
     * @param blogId
     * @param id
     * @param comment
     * @param model
     * @return
     */
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String deleteComment(@PathVariable Long blogId, @PathVariable Long id, Comment comment, Model model) {
        commentService.deleteComment(comment,id);
        DetailedBlog blogDetailById = blogService.getBlogDetailById(blogId);
        List<Comment> comments = commentService.getListCommentByBlogId(blogId);
        model.addAttribute("blog", blogDetailById);
        model.addAttribute("comments", comments);
        return "blog";
    }


}

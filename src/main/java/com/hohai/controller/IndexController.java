package com.hohai.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hohai.entity.Comment;
import com.hohai.queryvo.DetailedBlog;
import com.hohai.queryvo.FirstPageBlog;
import com.hohai.queryvo.RecommendBlog;
import com.hohai.service.BlogService;
import com.hohai.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author jin
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    /**
     * 进入首页后，查询推荐博客和首页博客,并展示在首页
     * @return
     */
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 10);
        List<FirstPageBlog> firstPageBlogs = blogService.getAllFirstPageBlog();
        List<RecommendBlog> recommendedBlog = blogService.getAllRecommendBlog();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(firstPageBlogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("recommendedBlog", recommendedBlog);
//        System.out.println(recommendedBlog);
        return "index";
    }

    /**
     * 首页搜索功能
     * @param model
     * @param query
     * @param pageNum
     * @return
     */
    @PostMapping("/search")
    public String search(Model model, @RequestParam String query, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<FirstPageBlog> list = blogService.indexSearch(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    /**
     * 统计博客信息
     * @return
     */
    @GetMapping("/footer/blogmessage")
    public String getBlogMessage(Model model){
        int blogCount = blogService.getBlogCount();
        int viewCount = blogService.getViewCount();
        int commentCount = blogService.getCommentCount();
        int messageCount = blogService.getMessageCount();

        model.addAttribute("blogCount", blogCount);
        model.addAttribute("viewCount", viewCount);
        model.addAttribute("commentCount", commentCount);
        model.addAttribute("messageCount", messageCount);
        return "index :: blogMessage";
    }


    /**
     * 查看博客详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}")
    public String blogDetail(@PathVariable Long id , Model model){
        DetailedBlog detailedBlog = blogService.getBlogDetailById(id);
        List<Comment> comments = commentService.getListCommentByBlogId(id);
        model.addAttribute("blog", detailedBlog);
        model.addAttribute("comments", comments);
        return "blog";
    }

}

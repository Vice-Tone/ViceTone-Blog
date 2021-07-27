package com.hohai.service.impl;

import com.hohai.NotFoundException;
import com.hohai.dao.BlogDao;
import com.hohai.dao.BlogSearchDao;
import com.hohai.entity.Blog;
import com.hohai.queryvo.*;
import com.hohai.service.BlogService;
import com.hohai.util.MarkdownUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/24 9:39 上午
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogDao blogDao;

    @Resource
    private BlogSearchDao blogSearchDao;

    @Override
    public List<BlogQuery> getAllBlogs() {
        return blogDao.getAllBlogs();
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        //存入ES
        blogSearchDao.save(blog);
        return blogDao.saveBlog(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public EditBlog findBlogById(Long id) {
        return blogDao.findBlogById(id);
    }

    @Override
    public int updateBlog(EditBlog editBlog) {
        editBlog.setUpdateTime(new Date());
        return blogDao.updateBlog(editBlog);
    }

    @Override
    public List<BlogQuery> searchBlog(SearchBlog searchBlog) {
        return blogDao.searchBlog(searchBlog);
    }

    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogDao.getAllFirstPageBlog();
    }

    @Override
    public List<RecommendBlog> getAllRecommendBlog() {
        return blogDao.getAllRecommendBlog();
    }

    @Override
    public List<FirstPageBlog> indexSearch(String query) {
        return blogDao.indexSearch(query);
    }

    @Override
    public int getBlogCount() {
        return blogDao.getBlogCount();
    }

    @Override
    public int getViewCount() {
        return blogDao.getViewCount();
    }

    @Override
    public int getCommentCount() {
        return blogDao.getCommentCount();
    }

    @Override
    public int getMessageCount() {
        return blogDao.getMessageCount();
    }

    @Override
    public DetailedBlog getBlogDetailById(Long id) {
        DetailedBlog detailedBlog = blogDao.getBlogDetailById(id);
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        blogDao.updateViews(id);
        blogDao.updateCommentCount(id);

        return detailedBlog;
    }

    @Override
    public List<FirstPageBlog> getBlogByTypeId(Long id) {
        return blogDao.getBlogByTypeId(id);
    }
}

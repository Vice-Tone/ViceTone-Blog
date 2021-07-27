package com.hohai.service;

import com.hohai.entity.Blog;
import com.hohai.queryvo.*;

import java.util.List;

public interface BlogService {
    List<BlogQuery> getAllBlogs();

    int saveBlog(Blog blog);

    void deleteBlog(Long id);

    EditBlog findBlogById(Long id);

    int updateBlog(EditBlog editBlog);

    List<BlogQuery> searchBlog(SearchBlog searchBlog);

    List<FirstPageBlog> getAllFirstPageBlog();

    List<RecommendBlog> getAllRecommendBlog();

    List<FirstPageBlog> indexSearch(String query);

    int getBlogCount();

    int getViewCount();

    int getCommentCount();

    int getMessageCount();

    DetailedBlog getBlogDetailById(Long id);

    List<FirstPageBlog> getBlogByTypeId(Long id);
}

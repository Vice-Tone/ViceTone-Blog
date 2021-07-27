package com.hohai.dao;

import com.hohai.entity.Blog;
import com.hohai.queryvo.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jin
 */
@Repository
public interface BlogDao{

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

    void updateViews(Long id);

    void updateCommentCount(Long id);

    List<FirstPageBlog> getBlogByTypeId(Long typeId);
}

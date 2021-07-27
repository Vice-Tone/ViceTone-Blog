package com.hohai.dao;

import com.hohai.entity.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ：jin
 * @description: 描述:
 * @date ：Created in 2021/7/28 00:39
 */
@Repository
public interface BlogSearchDao extends ElasticsearchRepository<Blog, Long> {

}

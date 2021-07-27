package com.hohai;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@SpringBootTest
class VicetoneBlogApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    void contextLoads() {
    }


}

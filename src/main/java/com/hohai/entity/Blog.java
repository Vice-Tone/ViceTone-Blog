package com.hohai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author ：jin
 * @date ：Created in 2020/9/22 8:52 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document(indexName ="blog")
public class Blog {
    //主键id
    @Id
    private Long id;
    //博客标题
    @Field(type = FieldType.Text)
    private String title;
    //博客内容
    @Field(type = FieldType.Text)
    private String content;
    //首图地址
    private String firstPicture;
    //标记是否原创
    private String flag;
    //浏览次数
    private Integer views;
    //评论次数
    private Integer commentCount;
    //是否开启赞赏
    private boolean appreciation;
    //是否开启版权
    private boolean shareStatement;
    //是否开启评论
    private boolean commentabled;
    //是否发布
    private boolean published;
    //是否推荐
    private boolean recommend;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //博客描述
    private String description;


    //分类
    private Type type;
    //分类id
    private Long typeId;
    //用户
    private User user;
    //用户id
    private  Long userId;
    //评论集合
    private ArrayList<Comment> comments = new ArrayList<>();
}

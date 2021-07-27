package com.hohai.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author ：jin
 * @date ：Created in 2020/9/29 10:45 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FirstPageBlog {
    //Blog
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private String description;

    //Type
    private String typeName;

    //User
    private String nickname;
    private String avatar;
}

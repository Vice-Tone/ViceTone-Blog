package com.hohai.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author ：jin
 * @date ：Created in 2020/10/4 4:20 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DetailedBlog {
    private Long id;
    private String firstPicture;
    private String flag;
    private String title;
    private String content;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private boolean commentabled;
    private boolean shareStatement;
    private boolean appreciation;

    private String nickname;
    private String avatar;

    private String typeName;

}

package com.hohai.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author ：jin
 * @date ：Created in 2020/9/24 9:53 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EditBlog {
    private Long id;
    private String flag;
    private String title;
    private String content;
    private Long typeId;
    private String firstPicture;
    private String description;
    private boolean recommend;
    private boolean published;
    private boolean shareStatement;
    private boolean appreciation;
    private boolean commentabled;
    private Date updateTime;
}

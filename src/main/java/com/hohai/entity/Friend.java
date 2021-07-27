package com.hohai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author ：jin
 * @date ：Created in 2020/9/22 9:36 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Friend {
    //主键
    private Long id;
    //博客名
    private String blogName;
    //博客地址
    private String blogAddress;
    //图片地址
    private String pictureAddress;
    //创建时间
    private Date createTime;
}

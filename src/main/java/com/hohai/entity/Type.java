package com.hohai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/22 9:37 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Type {
    //主键
    private Long id;
    //分类名
    private String name;
    //该分类下的博客
    private List<Blog> blogs = new ArrayList<>();
}

package com.hohai.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author ：jin
 * @date ：Created in 2020/9/29 10:44 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RecommendBlog {
    
    private Long id;
    private String title;
    private String firstPicture;
    private boolean recommend;

}

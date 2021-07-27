package com.hohai.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author ：jin
 * @date ：Created in 2020/9/25 11:15 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SearchBlog {
    private String title;
    private Long typeId;
}

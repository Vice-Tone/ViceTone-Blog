package com.hohai.queryvo;

import com.hohai.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author ：jin
 * @date ：Created in 2020/9/24 12:23 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BlogQuery {

    private Long id;
    private String title;
    private Date updateTime;
    private Boolean recommend;
    private Boolean published;
    private Long typeId;
    private Type type;

}

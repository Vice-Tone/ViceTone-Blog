package com.hohai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author ：jin
 * @date ：Created in 2020/9/22 9:37 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Picture {
    //主键
    private Long id;
    //图片名
    private String picturename;
    //图片时间
    private String picturetime;
    //图片地址
    private String pictureaddress;
    //图片描述
    private String picturedescription;

}

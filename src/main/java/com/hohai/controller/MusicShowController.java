package com.hohai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ：jin
 * @date ：Created in 2020/10/6 7:57 下午
 */
@Controller
public class MusicShowController {

    @GetMapping("/music")
    public String musicBox(){
        return "music";
    }
}

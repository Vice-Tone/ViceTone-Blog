package com.hohai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ：jin
 * @date ：Created in 2020/10/6 3:53 下午
 */
@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}

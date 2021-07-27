package com.hohai.controller;

import com.hohai.queryvo.BlogQuery;
import com.hohai.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/10/6 6:49 下午
 */
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archive(Model model){
        List<BlogQuery> allBlogs = blogService.getAllBlogs();
        model.addAttribute("blogs", allBlogs);
        return "archives";
    }

}

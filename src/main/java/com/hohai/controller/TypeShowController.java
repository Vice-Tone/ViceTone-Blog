package com.hohai.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hohai.entity.Type;
import com.hohai.queryvo.FirstPageBlog;
import com.hohai.service.BlogService;
import com.hohai.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/30 10:13 上午
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    /**
     * 根据id查询分类，并查询该分类下的所有博客
     * @param pageNum
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @PathVariable Long id, Model model){
        List<Type> types = typeService.getAllTypeAndBlog();
        //-1代表从首页过来
        if (id == -1) {
            id = types.get(0).getId();
        }
        List<FirstPageBlog> blogs = blogService.getBlogByTypeId(id);
        PageHelper.startPage(pageNum, 10000);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }

}

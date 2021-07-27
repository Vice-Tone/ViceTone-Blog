package com.hohai.controller;

import com.hohai.entity.Picture;
import com.hohai.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/10/7 2:27 下午
 */
@Controller
public class PictureShowController {

    @Autowired
    private PictureService pictureService;

    @GetMapping("/picture")
    public String getPicture(Model model){
        List<Picture> pictures = pictureService.getAll();
        model.addAttribute("pictures", pictures);
        return "picture";
    }
}

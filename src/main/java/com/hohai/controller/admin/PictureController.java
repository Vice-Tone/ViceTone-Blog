package com.hohai.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hohai.entity.Picture;
import com.hohai.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/28 10:00 上午
 */
@Controller
@RequestMapping("/admin")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    /**
     * 查询所有照片
     *
     * @return
     */
    @GetMapping("/pictures")
    public String pictures(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                           Model model) {
        PageHelper.startPage(pageNum, 10);
        List<Picture> list = pictureService.getAll();
        PageInfo<Picture> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/pictures";
    }

    /**
     * 跳转新增照片页面
     *
     * @return
     */
    @RequestMapping("/pictures/input")
    public String pictureInput(Model model) {
        model.addAttribute("picture", new Picture());
        return "admin/pictures-input";
    }

    /**
     * 新增照片
     *
     * @return
     */
    @PostMapping("/pictures")
    public String addPicture(Picture picture, RedirectAttributes redirectAttributes) {
        int statNum = pictureService.addPicture(picture);
        if (statNum == 1) {
            redirectAttributes.addFlashAttribute("message", "新增成功");
        } else {
            redirectAttributes.addFlashAttribute("message", "新增失败");
        }
        return "redirect:/admin/pictures";
    }


    /**
     * 根据id删除照片
     *
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/pictures/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        pictureService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/pictures";
    }

    /**
     * 根据id查询照片
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/pictures/{id}/input")
    public String findPictureById(@PathVariable Long id,Model model){
        Picture picture = pictureService.findPictureById(id);
        model.addAttribute("picture", picture);
        return "admin/pictures-input";
    }

    /**
     * 更新照片
     *
     * @param picture
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/pictures/{id}")
    public String update(Picture picture, RedirectAttributes redirectAttributes) {
        int statNum = pictureService.update(picture);
        if (statNum == 1) {
            redirectAttributes.addFlashAttribute("message", "更新成功");
        } else {
            redirectAttributes.addFlashAttribute("message", "更新失败");
        }
        return "redirect:/admin/pictures";
    }

}

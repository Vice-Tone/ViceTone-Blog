package com.hohai.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hohai.entity.Type;
import com.hohai.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/22 12:24 下午
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 分页查询所有分类
     * @param model
     * @param pageNum 起始页
     * @return
     */
    @GetMapping("/getAllType")
    public String getAllType(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Type> list = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }


    /**
     * 前往新增type页面
     */
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    /**
     * 新增分类，首先根据type的name属性查询，若查到则不能新增
     * @param type
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/addType")
    public String add(@Valid Type type, RedirectAttributes redirectAttributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            redirectAttributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int number = typeService.addType(type);
        if (number == 1) {
            redirectAttributes.addFlashAttribute("message", "添加成功");
        } else {
            redirectAttributes.addFlashAttribute("message", "添加失败");
            return "redirect:/admin/types/input";
        }

        return "redirect:/admin/getAllType";
    }

    /**
     * 删除分类，根据id删除
     * @param id 分类id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/getAllType";
    }

    /**
     * 先根据id进行查询，将查出的分类名显示出来，然后update
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/types/{id}/update")
    public String findTypeById(@PathVariable Long id, Model model) {
        Type type = typeService.getTypeById(id);
        model.addAttribute("type", type);
        return "admin/types-input";
    }

    /**
     * 更新分类
     * @param type
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/update/{id}")
    public String updateType(Type type, RedirectAttributes redirectAttributes) {
        typeService.update(type);
        redirectAttributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/getAllType";
    }

}

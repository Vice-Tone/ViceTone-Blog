package com.hohai.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hohai.entity.Friend;
import com.hohai.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/9/26 9:03 上午
 */
@Controller
@RequestMapping("/admin")
public class FriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;

    /**
     * 分页查询所有友链
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/friendlinks")
    public String getAllFriendLink(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                   Model model) {
        PageHelper.startPage(pageNum, 10);
        List<Friend> list = friendLinkService.getAll();
        PageInfo<Friend> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/friendlinks";
    }

    /**
     * 跳转至新增友链页面
     * @param model
     * @return
     */
    @GetMapping("/friendlinks/input")
    public String input(Model model){
        model.addAttribute("friendlink", new Friend());
        return "admin/friendlinks-input";
    }


    /**
     * 新增友链
     * @param friend
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/friendlinks")
    public String saveFriendLink(Friend friend, RedirectAttributes redirectAttributes) {
        friend.setCreateTime(new Date());
        int statNum = friendLinkService.save(friend);
        if (statNum == 1) {
            redirectAttributes.addFlashAttribute("message", "新增成功");
        } else if (statNum == 0) {
            redirectAttributes.addFlashAttribute("message", "新增失败");
        }
        return "redirect:/admin/friendlinks";
    }

    /**
     * 根据id删除
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/friendlinks/{id}/delete")
    public String deleteFriend(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        friendLinkService.deleteByID(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/friendlinks";
    }

    /**
     * 根据id查询
     * @param id
     * @param model
     * @return
     */
    @GetMapping("friendlinks/{id}/input")
    public String findFriend(@PathVariable Long id, Model model) {
        Friend friend = friendLinkService.findById(id);
        model.addAttribute("friendlink", friend);
        return "admin/friendlinks-input";
    }

    /**
     * 更新友链
     * @param friend
     * @param redirectAttributes
     * @return
     */
    @PostMapping("friendlinks/{id}")
    public String update(Friend friend, RedirectAttributes redirectAttributes) {
        int statNum = friendLinkService.update(friend);
        if (statNum == 1) {
            redirectAttributes.addFlashAttribute("message", "更新成功");
        } else if (statNum == 0) {
            redirectAttributes.addFlashAttribute("message", "更新失败");
        }
        return "redirect:/admin/friendlinks";
    }

}

package com.hohai.controller;

import com.hohai.entity.Friend;
import com.hohai.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/10/7 2:05 下午
 */
@Controller
public class FriendShowController {

    @Autowired
    private FriendLinkService friendLinkService;

    @GetMapping("/friends")
    public String showFriends(Model model){
        List<Friend> friendlinks = friendLinkService.getAll();
        model.addAttribute("friendlinks", friendlinks);
        return "friends";
    }

}

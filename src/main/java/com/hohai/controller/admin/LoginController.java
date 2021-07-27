package com.hohai.controller.admin;

import com.hohai.entity.User;
import com.hohai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author jin
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转登录页面
     * @return login页面
     */
    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    /**
     * 登录验证功能
     * @param username 用户名
     * @param password 密码
     * @param request 请求域
     * @param attributes 重定向附带参数
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, RedirectAttributes attributes) {
        User user = userService.findByUsernameAndPassword(username, password);
        if (user != null) {
            //将密码置空，以防安全问题
            user.setPassword(null);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 注销功能
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/admin";
    }

//    测试用
//    @RequestMapping("test2")
//    @ResponseBody
//    public String test() {
//        return "test ok";
//    }
}

package com.hohai.controller;

import com.hohai.entity.Message;
import com.hohai.entity.User;
import com.hohai.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ：jin
 * @date ：Created in 2020/10/6 8:11 下午
 */
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Value("${message.avatar}")
    private String avatar;

    /**
     * 跳转message页面
     *
     * @return
     */
    @GetMapping("/message")
    public String toMessage() {
        return "message";
    }


    /**
     * 跳转至message页面后，加载留言内容
     * @param model
     * @return
     */
    @GetMapping("/messagecomment")
    public String getMessage(Model model){
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message :: messageList";
    }


    /**
     * 新增留言,然后查询所有流言
     *
     * @param message
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/message")
    public String messageInput(Message message, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        } else {
            message.setAvatar(avatar);
        }
        if (message.getParentMessage().getId() != null) {
            message.setParentMessageId(message.getParentMessage().getId());
        }
        messageService.saveMessage(message);
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message :: messageList";
    }


    @RequestMapping("/message/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        messageService.deleteMessage(id);
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message :: messageList";
    }


}

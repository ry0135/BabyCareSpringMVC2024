package com.example.controller;

import com.example.model.*;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ChatMessageController {
    @Autowired
    private MessageService messageService;
    @GetMapping("/chatMessage")
    public String viewCart(HttpSession session,
                           @RequestParam(name = "toId", required = false) String toId,
                           Model model) {
        Account user = (Account) session.getAttribute("account");

        if (user == null) {
            return "redirect:/login"; // Chuyển hướng đến trang đăng nhập
        }

        String idFrom = user.getUserID();
        List<ChatPartnerDTO> listLasstmessages = messageService.getLatestChatMessages(idFrom);
        List<MessageDTO> messages = messageService.getMessagesBetween2(toId, idFrom);
        model.addAttribute("messages", messages);
        model.addAttribute("toId", toId);
        model.addAttribute("idFrom", idFrom);
        model.addAttribute("listLasstmessages", listLasstmessages);


        return "testSocket"; // Trả về tên của view cart.jsp
    }

}

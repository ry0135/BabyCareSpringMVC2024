package com.example.controller;

import com.example.model.Message;
import com.example.model.Notification;
import com.example.repository.NotificationRepository;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Date;
@Controller
public class WebsocketController {

    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(value = "/testSocket")
    public String home(){
        return "testSocket";
    }

    @Autowired
    private MessageService messageService;  // Thêm service để lưu message

    public void sendMessageToUser(String toId, Message message) {
        Notification notification = new Notification();
        notification.setToId(toId);
        notification.setMessage(message.getMessageText());
        notification.setFromId(message.getFromId());
        // Gửi tin nhắn chính
        template.convertAndSendToUser(toId, "/queue/messages", message);

        // Gửi thông báo đến người nhận rằng họ có tin nhắn mới
//        Notification notification = new Notification("Bạn có tin nhắn mới từ " + message.getFromId());
        template.convertAndSendToUser(toId, "/queue/notifications", notification);
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message greeting(Message message) throws Exception {
        // Đặt timestamp cho message
        message.setTimestamp(new Date());

        // Lưu message vào database
        messageService.saveMessage(message);

        // Gửi lại message cho tất cả client lắng nghe
        return message;  // Gửi lại chính message
    }

    @RequestMapping(value = "/simMessage")
    public String sendGreeting() throws Exception {
        Message message = new Message();
        message.setToId("User");
        message.setFromId("Server");
        message.setMessageText("Hello, Other!");
        message.setTimestamp(new Date());

        // Gửi message đến tất cả client
        template.convertAndSend("/topic/messages", message);
        return "testSocket"; // Render sample view
    }


    @MessageMapping("/notifications") // Có thể là một địa chỉ khác cho thông báo
    public void sendNotification(Notification notification) {
        // Xử lý thông báo và gửi đến người dùng cụ thể
        template.convertAndSendToUser(notification.getToId(), "/queue/notifications", notification); // Gửi thông báo đến người dùng cụ thể
    }

}





//package com.example.controller;
//
//        import com.example.model.Message;
//        import com.example.model.Notification;
//        import com.example.repository.NotificationRepository;
//        import com.example.service.MessageService;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.messaging.handler.annotation.MessageMapping;
//        import org.springframework.messaging.handler.annotation.SendTo;
//        import org.springframework.messaging.simp.SimpMessagingTemplate;
//        import org.springframework.stereotype.Controller;
//        import org.springframework.web.bind.annotation.RequestMapping;
//
//
//        import java.util.Date;
//@Controller
//public class WebsocketController {
//
//    @Autowired
//    private SimpMessagingTemplate template;
//    @Autowired
//    private NotificationRepository notificationRepository;
//    @RequestMapping(value = "/testSocket")
//    public String home(){
//        return "testSocket";
//    }
//
//    @Autowired
//    private MessageService messageService;  // Thêm service để lưu message
//
//    public void sendMessageToUser(String toId, Message message) {
//        Notification notification = new Notification();
//        notification.setToId(toId);
//        notification.setMessage(message.getMessageText());
//        notification.setFromId(message.getFromId());
//        // Gửi tin nhắn chính
//        template.convertAndSendToUser(toId, "/queue/messages", message);
//
//        // Gửi thông báo đến người nhận rằng họ có tin nhắn mới
////        Notification notification = new Notification("Bạn có tin nhắn mới từ " + message.getFromId());
//        template.convertAndSendToUser(toId, "/queue/notifications", notification);
//    }
//
//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public Message greeting(Message message) throws Exception {
//        // Đặt timestamp cho message
//        message.setTimestamp(new Date());
//        Notification notification = new Notification();
//        notification.setFromId(message.getFromId());
//        notification.setToId(message.getToId());
//        notification.setMessage(message.getMessageText());
//        notificationRepository.save(notification); // Lưu thông báo vào CSDL
//        // Lưu message vào database
//        messageService.saveMessage(message);
//
//        // Gửi lại message cho tất cả client lắng nghe
//        return message;  // Gửi lại chính message
//    }
//
//    @RequestMapping(value = "/simMessage")
//    public String sendGreeting() throws Exception {
//        Message message = new Message();
//        message.setToId("User");
//        message.setFromId("Server");
//        message.setMessageText("Hello, Other!");
//        message.setTimestamp(new Date());
//
//        // Gửi message đến tất cả client
//        template.convertAndSend("/topic/messages", message);
//        return "testSocket"; // Render sample view
//    }
//
//    @MessageMapping("/notifications")
//    public void sendNotification(Notification notification) {
//        // Lưu thông báo vào DB
//        notificationRepository.save(notification); // Lưu thông báo vào CSDL
//        template.convertAndSendToUser(notification.getToId(), "/queue/notifications", notification);
//    }
//
//
//}

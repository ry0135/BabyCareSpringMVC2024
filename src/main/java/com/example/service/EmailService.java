package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendCodeToEmail(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Mã Xác Minh");
        message.setText("Mã xác minh của bạn là: " + code);
        mailSender.send(message);
        System.out.println("Đã gửi mã thành công.");
    }
    public void sendVerifyCodeAndUpdatePassword(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Mã xác minh đặt lại mật khẩu");
        message.setText("Xin chào,\n\n"
                + "Bạn đã yêu cầu đặt lại mật khẩu cho tài khoản của mình. "
                + "Mã xác minh của bạn là: " + code + "\n\n"
                + "Vui lòng nhập mã này trên trang đặt lại mật khẩu để tiếp tục. "
                + "Nếu bạn không yêu cầu hành động này, hãy bỏ qua email này.\n\n"
                + "Trân trọng,\n"
                + "Đội ngũ hỗ trợ BabyCare");
        mailSender.send(message);
        System.out.println("Đã gửi email mã xác minh thành công.");
    }


}

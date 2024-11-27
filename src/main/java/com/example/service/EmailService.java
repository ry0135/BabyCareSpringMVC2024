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

    public void sendCodeToEmailResendCode(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Mã Xác Minh");
        message.setText("Mã xác minh của bạn đã được gửi lại là: " + code);
        mailSender.send(message);
        System.out.println("Đã gửi mã thành công.");
    }
}

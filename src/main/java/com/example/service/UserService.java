package com.example.service;

import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailService emailService;


    private String generateRandomCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }


}

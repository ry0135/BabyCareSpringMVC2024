package com.example.service;

import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
@Service
public class RandomService {

    @Autowired
    private AccountRepository accountRepository;
    @Transactional(readOnly = true)
    public  String getRandomUserID() {
        StringBuilder sb;
        do {
            sb = new StringBuilder();
            for (int i = 0; i < 5; i++) { // 5 số
                sb.append(new Random().nextInt(10));
            }
        } while (accountRepository.existsByUserID(sb.toString()));
        return sb.toString();
    }
    @Transactional(readOnly = true)
    public String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}

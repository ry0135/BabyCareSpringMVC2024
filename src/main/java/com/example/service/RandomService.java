
package com.example.service;

import com.example.repository.AccountRepository;
import com.example.repository.BillRepository;
import com.example.repository.BrandRepository;
import com.example.repository.CommentProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
@Service
public class RandomService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CommentProductRepository commentProductRepository;
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
    public  String getRandomBrandID() {
        StringBuilder sb ;
        do {
            sb = new StringBuilder();
            sb.append("B");

            for (int i = 1; i < 5; i++) {
                sb.append(new Random().nextInt(10));
            }
        }
        while(brandRepository.existsByBrandID(sb.toString()));
        return sb.toString();
    }

    @Transactional(readOnly = true)
    public String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    @Transactional(readOnly = true)
    public  String getRandomProductID() {
        StringBuilder sb ;
        do {
            sb = new StringBuilder();
            sb.append("P");

            for (int i = 1; i < 5; i++) {
                sb.append(new Random().nextInt(10));
            }
        }
        while(brandRepository.existsByBrandID(sb.toString()));
        return sb.toString();
    }

    @Transactional(readOnly = true)
    public  String getRandomBillID() {
        int length = 10;
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
        }
        return sb.toString();
    }


    @Transactional(readOnly = true)
    public  String getRandomCommentID() {
        StringBuilder sb ;
        do {
            sb = new StringBuilder();
            sb.append("CM");

            for (int i = 1; i < 4; i++) {
                sb.append(new Random().nextInt(10));
            }
        }
        while(commentProductRepository.existsBycommentID(sb.toString()));
        return sb.toString();
    }


}

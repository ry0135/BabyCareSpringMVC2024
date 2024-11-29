package com.example.service;

import com.example.repository.ShopServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class ShopServiceService {
    @Autowired
    private ShopServiceRepository shopServiceRepository; // Tiêm repository

    @Transactional(readOnly = true)
    public String getRandomBrandID() {
        StringBuilder sb;
        Random random = new Random(); // Khởi tạo Random một lần

        do {
            sb = new StringBuilder();

            // Tạo một chuỗi brandID dài 6 ký tự (hoặc có thể thay đổi độ dài)
            for (int i = 0; i < 5; i++) { // 6 ký tự
                // Chọn giữa số và chữ cái
                int randomChoice = random.nextInt(2);

                if (randomChoice == 0) {
                    sb.append(random.nextInt(10)); // Thêm số
                } else {
                    // Thêm ký tự chữ cái ngẫu nhiên (chữ hoa từ A-Z)
                    sb.append((char) ('A' + random.nextInt(26)));
                }
            }
        } while (shopServiceRepository.existsByBrandID(sb.toString())); // Kiểm tra xem ID đã tồn tại chưa

        return sb.toString(); // Trả về ID ngẫu nhiên
    }
}

package com.example.service;
import com.example.model.Product;
import com.example.repository.AccountRepository;

import com.example.repository.BillRepository;
import com.example.repository.OrderDetailsRepository;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailsRepository orderDetailRepository;

    public int getUserAmount() {
        return accountRepository.countUserByRole();
    }


    public int getCTVAmount() {
        // Sử dụng phương thức mới trong repository
        return accountRepository.countCTVByRole();
    }

    public int getNumberOfOrdersToConfirm() {
        // Sử dụng phương thức từ BillRepository
        return billRepository.countOrdersToConfirm();
    }

    public int getPendingOrdersCountCTV(String ctvId) {
        return billRepository.countPendingOrdersByCTV(ctvId);
    }

    public int getNumberOfProductLeft() {
        // Sử dụng phương thức từ ProductRepository
        return productRepository.countProductsLeft();
    }

    public double getOrderRevenue() {
        // Gọi phương thức từ repository với năm 2024
        return orderDetailRepository.calculateRevenueByYear(2024);
    }
    public double getOrderRevenueByYear(int year) {
        return orderDetailRepository.calculateRevenueByYear(year);
    }

    public double getMonthlyRevenueByAdmin(int month, int year) {
        return orderDetailRepository.calculateMonthlyRevenue(month, year);
    }

//    public List<Product> getListRankOfProductByCTVID(String ctvId) {
//        return orderDetailRepository.findRankedProductsByCTVId(ctvId);
//    }

    public List<Product> getListRankOfProduct() {
        return orderDetailRepository.findRankedProducts();
    }




}

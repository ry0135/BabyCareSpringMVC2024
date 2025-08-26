package com.example.controller.admin;
import java.text.DecimalFormat;
import java.util.List;

import com.example.service.StatisticsService;
import com.sun.org.apache.xerces.internal.util.StAXInputSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


@Controller
public class StatisticController {

    @Autowired
    private StatisticsService statisticsService;


    @GetMapping("/statistic")
    public String showStatistics(
            @RequestParam(value = "month", defaultValue = "1") int month,
            @RequestParam(value = "year", defaultValue = "2024") int year,
            @RequestParam(value = "yearRevenue", defaultValue = "2024") int yearRevenue,
            Model model) {

        // Lấy số liệu thống kê
        int numberOfUsers = statisticsService.getUserAmount();
        int numberOfCTV = statisticsService.getCTVAmount();
        int numberOfOrderToConfirm = statisticsService.getNumberOfOrdersToConfirm();
        int numberOfProductLeft = statisticsService.getNumberOfProductLeft();
        double orderRevenue = statisticsService.getOrderRevenue();

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String formattedOrderRevenue = formatter.format(orderRevenue);

       // List<Revenue> listProduct = statisticsRepository.getListRankOfProduct();

        // Lấy doanh thu theo năm
        double revenue2020 = statisticsService.getOrderRevenueByYear(2020);
        double revenue2021 = statisticsService.getOrderRevenueByYear(2021);
        double revenue2022 = statisticsService.getOrderRevenueByYear(2022);
        double revenue2023 = statisticsService.getOrderRevenueByYear(2023);
        double revenue2024 = statisticsService.getOrderRevenueByYear(2024);
        double revenue2025 = statisticsService.getOrderRevenueByYear(2025);
        double revenue2026 = statisticsService.getOrderRevenueByYear(2026);
        double revenue2027 = statisticsService.getOrderRevenueByYear(2027);
        double revenue2028 = statisticsService.getOrderRevenueByYear(2028);
        double revenue2029 = statisticsService.getOrderRevenueByYear(2029);
        double revenue2030 = statisticsService.getOrderRevenueByYear(2030);

        // Doanh thu theo tháng của năm chọn
        double[] monthlyRevenues = new double[12];
        for (int i = 0; i < 12; i++) {
            monthlyRevenues[i] = statisticsService.getMonthlyRevenueByAdmin(i + 1, yearRevenue);
        }

        // Gửi dữ liệu đến JSP
        model.addAttribute("numberOfUsers", numberOfUsers);
        model.addAttribute("numberOfCTV", numberOfCTV);
        model.addAttribute("numberOfOrderToConfirm", numberOfOrderToConfirm);
        model.addAttribute("numberOfProductLeft", numberOfProductLeft);
        model.addAttribute("orderRevenue", formattedOrderRevenue);
        //model.addAttribute("listProduct", listProduct);

        model.addAttribute("revenue2020", revenue2020);
        model.addAttribute("revenue2021", revenue2021);
        model.addAttribute("revenue2022", revenue2022);
        model.addAttribute("revenue2023", revenue2023);
        model.addAttribute("revenue2024", revenue2024);
        model.addAttribute("revenue2025", revenue2025);
        model.addAttribute("revenue2026", revenue2026);
        model.addAttribute("revenue2027", revenue2027);
        model.addAttribute("revenue2028", revenue2028);
        model.addAttribute("revenue2029", revenue2029);
        model.addAttribute("revenue2030", revenue2030);

        for (int i = 0; i < 12; i++) {
            model.addAttribute("m" + (i + 1), monthlyRevenues[i]);
        }

        model.addAttribute("yearRevenue", yearRevenue);
        model.addAttribute("month", month);
        model.addAttribute("year", year);

        return "admin"; // Trả về view tên "admin.jsp"
    }
}

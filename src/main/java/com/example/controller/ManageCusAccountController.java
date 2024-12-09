package com.example.controller;

import com.example.model.Account;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ManageCusAccountController {
    @Autowired
    private AccountService accountService;

    public ManageCusAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/manage-cus-account")
    public String manageCusAccount(Model model) {
        List<Account> listCusAcc = accountService.getListCusAccount();
        System.out.println(listCusAcc.size());
        model.addAttribute("listCusAcc", listCusAcc);
        return "managecustomeraccount"; // path to Thymeleaf template
    }
}

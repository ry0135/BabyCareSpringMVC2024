package com.example.controller.service;

import com.example.model.Account;
import com.example.model.ServiceEntity;
import com.example.service.ServiceService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ServiceListManagerController {
    @Autowired
    private ServiceService serviceService;
    @GetMapping("/service-list-manager")
    public String getServiceListManager(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            String ctvId =  account.getUserID() ;
            List<ServiceEntity> serviceList = serviceService.getServicesByCTVID(ctvId);
            model.addAttribute("serviceList", serviceList);
            return "servicebrand/service-list-manager";
        }
        return "redirect:/login";
    }

}

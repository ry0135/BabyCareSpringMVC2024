package com.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPageController {

    @GetMapping("landingPage")
    public String showLandingPage() {
        return "landingPage"; // Tên file JSP mà chúng ta sẽ tạo
    }
}

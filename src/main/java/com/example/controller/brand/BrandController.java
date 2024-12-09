package com.example.controller.brand;

import com.example.model.Account;
import com.example.model.Brand;
import com.example.repository.BrandRepository;
import com.example.service.*;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.required;

@Controller
public class BrandController {

    @GetMapping("/sellChanel")
    public String showSellChanel() {
        return "brand/sellChannel"; // View sẽ hiển thị form thêm sản phẩm
    }


}

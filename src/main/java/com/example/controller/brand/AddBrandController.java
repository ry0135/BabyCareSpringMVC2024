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
public class AddBrandController {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandService brandService;

    @Autowired
    private FilleUtils filleUtils;

    @Autowired
    private RandomService randomService;
    @GetMapping("/registerctv")
    public String getRegisterCTV(HttpServletRequest request, Model model) {
        // Lấy session và kiểm tra người dùng
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("account");

        // Kiểm tra nếu người dùng chưa đăng nhập thì chuyển hướng đến login
        if (user == null) {
            return "redirect:login"; // Nên sử dụng URL hợp lệ cho redirect
        }

        String userID = user.getUserID();

        // Lấy thông tin thương hiệu
        Brand brand = brandService.getBrandByCTVId(userID);
        model.addAttribute("brand", brand);

        // Kiểm tra xem có đăng ký nào chưa hoàn tất
        boolean hasPending = brandService.hasPendingRegistration(userID);
        model.addAttribute("hasPending", hasPending);

        // Trả về tên view (JSP)
        return "addBrand/information_shop"; // Trả về đường dẫn tới JSP
    }


    @PostMapping("/addBrand")
    public String saveBrand(HttpServletRequest request, Model model,
                            @RequestParam String accountNumber,
                            @RequestParam String bankName,
                            @RequestParam String brandName,
                            @RequestParam String brandDescription,
                            @RequestParam String newAddress,
                            @RequestParam String brandAddress
                                                                    ) {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("account");

        if (user == null) {
            return "redirect:login";
        }
        System.out.println(newAddress);


        String userID = user.getUserID();

        try {
            // Kiểm tra xem thương hiệu đã tồn tại hay chưa
            Brand existingBrand = brandService.getBrandByCTVId(userID);

            if (existingBrand != null) {
                // Kiểm tra tên thương hiệu đã tồn tại hay chưa
                if (brandService.checkBrandNameExist(brandName, existingBrand.getBrandID())) {
                    model.addAttribute("thongbao1", "Tên cửa hàng đã tồn tại.");
                } else {
                    // Cập nhật thương hiệu
                    boolean updated = brandService.updateBrand(existingBrand.getBrandID(), brandName,
                            (newAddress != null && !newAddress.isEmpty()) ? newAddress : brandAddress,
                            bankName, accountNumber,brandDescription);
                    if (updated) {
                        model.addAttribute("thongbao", "Lưu thông tin thành công.");
                    } else {
                        model.addAttribute("thongbao", "Có lỗi xảy ra khi cập nhật cửa hàng. Vui lòng thử lại.");
                    }
                }
            } else {
                // Thêm mới thương hiệu
                if (brandService.existsByBrandName(brandName)) {
                    model.addAttribute("thongbao1", "Tên cửa hàng đã tồn tại.");
                } else {
                    String brandID = randomService.getRandomBrandID();
                    boolean added = brandService.addBrand(brandID, brandName,
                            (newAddress != null && !newAddress.isEmpty()) ? newAddress : brandAddress,
                            userID, bankName, accountNumber,brandDescription);
//                    if (added) {
//                        model.addAttribute("thongbao", "Lưu thông tin thành công");
//                    } else {
//                        model.addAttribute("thongbao", "Có lỗi xảy ra khi đăng ký cửa hàng. Vui lòng thử lại.");
//                    }
                }
            }

            return "redirect:/registerctv"; // Redirect to the appropriate page after processing
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("thongbao", "Có lỗi xảy ra. Vui lòng thử lạissssss.");
            return "redirect:/registerctv"; // Redirect on error
        }
    }




    @GetMapping("/getBrandIndentifi")
    public String getInformation_definition(HttpServletRequest request, Model model) {
        // Lấy session và kiểm tra người dùng
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("account");

        // Kiểm tra nếu người dùng chưa đăng nhập thì chuyển hướng đến login
        if (user == null) {
            return "redirect:login"; // Nên sử dụng URL hợp lệ cho redirect
        }

        String userID = user.getUserID();

        // Lấy thông tin thương hiệu
        Brand brand = brandService.getBrandByCTVId(userID);
        model.addAttribute("brand", brand);

        // Kiểm tra xem có đăng ký nào chưa hoàn tất
        boolean hasPending = brandService.hasPendingRegistration(userID);
        model.addAttribute("hasPending", hasPending);

        // Trả về tên view (JSP)
        return "addBrand/information_definition"; // Trả về đường dẫn tới JSP
    }


    @PostMapping(value = "/addBrandIndentifi", consumes = {"multipart/form-data"})
    public String addBrandIdentifi(
            @RequestParam String CCCDNumber,
            @RequestParam(value = "img_CCCD_Face", required = false) MultipartFile file1,
            @RequestParam(value = "img_CCCD", required = false) MultipartFile file2,
            HttpSession session,
            Model model) {

        Account user = (Account) session.getAttribute("account");
        if (user == null) {
            return "redirect:login"; // Chuyển hướng tới trang đăng nhập nếu người dùng không đăng nhập.
        }
//        String CCCDNumber = "123456123456";
        String userID = user.getUserID();

        // Xử lý upload file
        try {
            if (file1 == null || file1.isEmpty() || file2 == null || file2.isEmpty()) {
                session.setAttribute("thongbao", "Vui lòng chọn cả hai hình ảnh.");
                return "redirect:/addBrandIdentifi"; // Quay lại nếu không có hình ảnh
            }

            String filename1 =filleUtils.saveFile(file1);
            String filename2 = filleUtils.saveFile(file2); // Lưu file2

            // Tìm kiếm thương hiệu
            Brand existingBrand = brandService.getBrandByCTVId(userID);
            if (existingBrand == null) {
                session.setAttribute("thongbao", "Thương hiệu không tồn tại.");
                return "redirect:/getBrandIndentifi"; // Quay lại nếu không tìm thấy thương hiệu
            }

            // Kiểm tra xem CCCD đã tồn tại hay chưa
            if (brandService.existsByIdentifiNumber(CCCDNumber)) {
                session.setAttribute("thongbao", "Số CCCD/CMND đã tồn tại.");
                return "redirect:/getBrandIndentifi"; // Quay lại nếu CCCD đã tồn tại
            }

            // Cập nhật thông tin thương hiệu
            existingBrand.setIdentifiNumber(CCCDNumber);
            existingBrand.setIdentifiImg(filename1);
            existingBrand.setIdentifiImgFace(filename2);

            brandRepository.save(existingBrand); // Lưu thương hiệu đã cập nhật

            session.setAttribute("thongbao", "Lưu thông tin thành công.");

        } catch (IOException e) {
            session.setAttribute("thongbao", "Có lỗi xảy ra. Vui lòng thử lại.");
            return "redirect:/getBrandIndentifi"; // Quay lại nếu xảy ra lỗi khi tải lên
        } catch (Exception e) {
            session.setAttribute("thongbao", "Có lỗi xảy ra. Vui lòng thử lại.");
            return "redirect:/getBrandIndentifi"; // Quay lại nếu có lỗi không lường trước
        }

        return "redirect:/registerctv"; // Chuyển hướng đến danh sách sản phẩm
    }


}

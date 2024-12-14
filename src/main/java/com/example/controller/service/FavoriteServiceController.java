package com.example.controller.service;

import com.example.model.Account;

import com.example.model.FavoriteService;
import com.example.model.FavoriteServiceDTO;
import com.example.model.ServiceType;
import com.example.service.FavoriteServiceService;
import com.example.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FavoriteServiceController {

    @Autowired
    private FavoriteServiceService favoriteService;
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/addfavorite")
    public String addFavorite(@RequestParam int serviceID, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            // Lấy đối tượng Account từ session
            Account oldUser = (Account) session.getAttribute("account");

            // Kiểm tra người dùng đã đăng nhập chưa
            if (oldUser == null) {
                redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để thêm vào yêu thích");
                return "redirect:/login"; // Chuyển hướng đến trang đăng nhập
            }

            // Tạo đối tượng yêu thích
            FavoriteService favorite = new FavoriteService();
            favorite.setServiceID(serviceID);

            favorite.setUserID(oldUser.getUserID());

         //    Kiểm tra xem dịch vụ đã được thêm vào danh sách yêu thích chưa
//            boolean isAlreadyFavorite = favoriteService.isFavorite(oldUser.getUserID(), serviceID);
//            if (isAlreadyFavorite) {
//                redirectAttributes.addFlashAttribute("message", "Dịch vụ này đã nằm trong danh sách yêu thích");
//                return "redirect:/service"; // Chuyển hướng về trang dịch vụ
//            }

            // Thêm dịch vụ vào danh sách yêu thích
            favoriteService.addFavorite(favorite);

            // Thông báo thành công
            redirectAttributes.addFlashAttribute("message", "Thêm vào danh sách yêu thích thành công");
            return "redirect:/service"; // Chuyển hướng về trang dịch vụ
        } catch (Exception e) {
            // Xử lý lỗi và thêm thông báo lỗi vào RedirectAttributes
            redirectAttributes.addFlashAttribute("error", "Lỗi khi thêm yêu thích: " + e.getMessage());
            return "redirect:/service"; // Chuyển hướng về trang dịch vụ
        }
    }

    @GetMapping("/listfavorite")
    public String getFavorites(HttpSession session, Model model) {
        // Lấy thông tin người dùng từ session
        Account oldUser = (Account) session.getAttribute("account");
        if (oldUser == null) {
            return "redirect:/login"; // Đảm bảo người dùng phải đăng nhập
        }

        String userID = oldUser.getUserID();

        List<FavoriteServiceDTO> favorites = favoriteService.getFavoriteServiceByUserID(userID);
        List<ServiceType> listC = serviceService.getAllServiceType();

        model.addAttribute("ListS", favorites); // Thêm danh sách yêu thích vào mô hình
        model.addAttribute("listC", listC);
        return "service/favorite"; // Trả về trang favorites.html
    }
/// Xóa
    @GetMapping("/removefavorite")
    public String removeFavorite(@RequestParam int favoriteID) {
        favoriteService.removeFavorite(favoriteID);
        return "redirect:/listfavorite";
    }

}

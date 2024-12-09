package com.example.controller.service;

import com.example.model.Account;
import com.example.model.FavoriteService;
import com.example.service.FavoriteServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FavoriteServiceController {

    @Autowired
    private FavoriteServiceService favoriteService;


    @GetMapping("/addfavorite")
    public ResponseEntity<String> addFavorite(@RequestParam int serviceID, HttpSession session) {
        try {
            // Lấy đối tượng Account từ thông tin session
            Account oldUser = (Account) session.getAttribute("account");

            // Kiểm tra nếu oldUser không null để tránh NullPointerException
            if (oldUser == null) {
                return ResponseEntity.status(403).body("Người dùng không đăng nhập");
            }

            // Tạo đối tượng yêu thích
            FavoriteService favorite = new FavoriteService();
            favorite.setServiceID(serviceID);
            favorite.setUserID(oldUser.getUserID()); // Lấy userID từ đối tượng Account

            // Lưu vào cơ sở dữ liệu
            favoriteService.addFavorite(favorite);

            return ResponseEntity.ok("Thêm thành công"); // Trả về phản hồi thành công
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi khi thêm yêu thích: " + e.getMessage());
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
        List<FavoriteService> favorites = favoriteService.listFavorites(userID);

        model.addAttribute("favorites", favorites); // Thêm danh sách yêu thích vào mô hình
        return "service/favorite"; // Trả về trang favorites.html
    }
/// Xóa
    @GetMapping("/removefavorite")
    public String removeFavorite(@RequestParam int favoriteID) {
        favoriteService.removeFavorite(favoriteID);
        return "redirect:/listfavorite";
    }

}

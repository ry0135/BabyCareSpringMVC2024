package com.example.controller;

import com.example.model.SearchHistory;
import com.example.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class SearchHistoryController {

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;


    @PostMapping("/saveSearchHistory") // Changed the URL mapping
    @ResponseBody
    public ResponseEntity<String> saveSearchHistory(@RequestParam("searchTerm") String searchTerm,
                                                    HttpSession session) {
        Integer userID = (Integer) session.getAttribute("UserID"); //Retrieve UserID from session

        if (userID != null && searchTerm != null && !searchTerm.isEmpty()) {
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setUserID(userID);
            searchHistory.setSearchTerm(searchTerm);
            searchHistoryRepository.save(searchHistory);
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: User ID or search term is missing.");
        }
    }
}

package com.example.controller.brand;

import com.example.model.Category;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class SubCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/fetchSubcategories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getSubcategories(@RequestParam("parent_id") Integer parentId) {
        List<Category> categories = categoryService.fetchSubcategories(parentId);
        return categories; // Spring tự động chuyển đổi đối tượng thành JSON
    }

}

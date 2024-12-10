package com.example.service;

import com.example.model.Category;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listCategoriesParent() {
        return categoryRepository.findCategoriesByLevelOne();
    }

    public List<Category> fetchSubcategories(Integer parentId) {
        return categoryRepository.findByParentId(parentId); // Phương thức tìm subcategories
    }
//    public List<Category> getAllCategories() {
//        List<Category> categories = categoryRepository.findAll();
//        System.out.println("Categories: " + categories); // Logging để kiểm tra dữ liệu
//        return categories;
//    }
}
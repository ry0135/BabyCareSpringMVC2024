package com.example.repository;


import com.example.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.level = 1")
    List<Category> findCategoriesByLevelOne();

    List<Category> findByParentId(Integer parentId); // Đặt lại tên phương thức cho đúng
}
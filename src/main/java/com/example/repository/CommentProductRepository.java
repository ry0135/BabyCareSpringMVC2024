package com.example.repository;

import com.example.model.CommentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentProductRepository extends JpaRepository<CommentProduct,String> {
        List<CommentProduct> findByProductID(String productID);
}

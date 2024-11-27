package com.example.service;

import com.example.model.Product;
import com.example.model.ProductImage;
import com.example.repository.ProductImageRepository;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

//        public List<Product> getAllProduct() {
//            List<Product> products = productRepository.findByStatus(1);
//            for (Product product : products) {
//                List<ProductImage> images = productImageRepository.findByProductID(product.getProductId().toString());
//                for (ProductImage image : images) {
//                    product.addImagePath(image.getImagePath());
//                }
//            }
//            return products;
//        }
        @Transactional
        public List<Product> getAllProduct() {
        // Lấy danh sách sản phẩm có trạng thái là 1
        List<Product> products = productRepository.findByStatus(1);

        // Duyệt qua từng sản phẩm và gán danh sách hình ảnh
        products.forEach(product -> {
            // Lấy danh sách hình ảnh theo ProductID
            List<ProductImage> images = productImageRepository.findByProductID(product.getProductId());

            // Thêm từng đường dẫn hình ảnh vào sản phẩm
            images.forEach(image -> product.addImagePath(image.getImagePath()));
        });

        return products;
    }

}

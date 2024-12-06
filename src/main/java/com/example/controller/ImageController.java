package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.example.service.FilleUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/image")
public class ImageController {


    @GetMapping("/{imageName:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        // Lấy dữ liệu ảnh từ file hệ thống
        byte[] imageBytes = FilleUtils.getImage(imageName);

        // Lấy phần mở rộng của file để xác định loại MIME
        String extension = imageName.substring(imageName.lastIndexOf(".") + 1);
        MediaType mediaType = getMediaTypeForImage(extension);

        // Trả về ảnh với MIME type phù hợp
        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(imageBytes);
    }

    // Hàm xác định MediaType dựa trên phần mở rộng của file ảnh
    private MediaType getMediaTypeForImage(String extension) {
        switch (extension.toLowerCase()) {
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
            case "jpeg":
                return MediaType.IMAGE_JPEG;
            case "gif":
                return MediaType.IMAGE_GIF;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;  // Nếu không xác định được, trả về mặc định
        }
    }
}
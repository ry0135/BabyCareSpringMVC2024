package com.example.controller;

import java.io.IOException;

import com.example.service.FilleUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController {

    @GetMapping("/{imageName:.+}")
    public byte[] getImage(@PathVariable(name = "imageName") String imageName) throws IOException {
        byte[] image = FilleUtils.getImage(imageName);
        return image;
    }
}
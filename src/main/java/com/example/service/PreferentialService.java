package com.example.service;

import com.example.model.Preferential;
import com.example.repository.PreferentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class PreferentialService {

    @Autowired
    private PreferentialRepository preferentialRepository;
    public Optional<Preferential> getPreferentialsByCode(String preferentialCode) {
        return preferentialRepository.findById(preferentialCode);
    }

    public Preferential addPreferential(Preferential preferential, MultipartFile file, String uploadDir, String createdBy, String discountText) throws IOException {
        // Check if the preferential code already exists
        if (preferentialRepository.existsByPreferentialCode(preferential.getPreferentialCode())) {
            throw new IllegalArgumentException("Preferential Code đã tồn tại");
        }

        // Process the uploaded image
        if (!file.isEmpty()) {
            String originalFileName = file.getOriginalFilename();
            String filePath = uploadDir + File.separator + originalFileName;

            // Create the upload directory if it doesn't exist
            Files.createDirectories(Paths.get(uploadDir));
            File uploadFile = new File(filePath);
            file.transferTo(uploadFile);

            // Modify the image (e.g., adding text)
            BufferedImage image = ImageIO.read(uploadFile);
            Graphics2D g2d = image.createGraphics();
            g2d.setFont(new Font("Arial", Font.BOLD, 64)); // Set font size and style
            g2d.setColor(Color.RED); // Set text color
            g2d.drawString(discountText, 50, 100); // Add text to the image
            g2d.dispose();

            // Save the modified image with a new name
            String outputFileName = "output_" + originalFileName;
            File outputFile = new File(uploadDir + File.separator + outputFileName);
            ImageIO.write(image, "png", outputFile);

            // Set the image name in the preferential object
            preferential.setPreferentialImg(outputFileName);
        }

        // Set createdBy and save the preferential to the database
        preferential.setEmployeeID(createdBy);
        return preferentialRepository.save(preferential);
    }

    public List<Preferential> getPreferentialsByPage(int page) {
        return preferentialRepository.findAllPaginated(page);
    }
    public void deletePreferential(String preferentialCode) {
        preferentialRepository.deleteById(preferentialCode);
    }

    public long getPreferentialCount() {
        return preferentialRepository.countPreferentials();
    }

    public Optional<Preferential> getValidPreferential(String preferentialID) {
        return preferentialRepository.findValidPreferential(preferentialID);
    }
    @Transactional
    public double getDiscountPercent(String preferentialID) {
        Double discountPercent = preferentialRepository.findDiscountPercent(preferentialID);
        return (discountPercent != null) ? discountPercent : 0.0; // Trả về 1.0 nếu không tìm thấy
    }

    public void decreasePreferentialQuantity(String preferentialID) {
        Optional<Preferential> optionalPreferential = preferentialRepository.findById(preferentialID);
        if (optionalPreferential.isPresent()) {
            Preferential preferential = optionalPreferential.get();
            if (preferential.getQuantity() > 0) {
                preferential.setQuantity(preferential.getQuantity() - 1);
                preferentialRepository.save(preferential);
            }
        }
    }

    public void savePreferential(Preferential preferential) {
        preferentialRepository.save(preferential);
    }
    public List<Preferential> getAllPreferentials() {
        return preferentialRepository.findAll();
    }
}

package com.example.controller.preferentials;

import com.example.model.Preferential;
import com.example.service.FilleUtils;
import com.example.service.PreferentialService;
import com.microsoft.sqlserver.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Controller
public class PreferentialController {
    @Autowired
    private File outputDirectory;
    @Autowired
    private PreferentialService preferentialService;

    // This method fetches all the preferentials from the database
    @GetMapping("/preferentials")
    public String getAllPreferentials(Model model) {
        List<Preferential> preferentials = preferentialService.getAllPreferentials();
        model.addAttribute("preferentialList", preferentials);
        return "preferential/preferentials"; // Returns the view name
    }

    @GetMapping("/preferential")
    public String getPreferentialForm(Model model) {
        // Trả về form thêm ưu đãi
        return "preferential/preferential_add";
    }

    @PostMapping("/addPreferential")
    public String addPreferential(
            @RequestParam("preferentialCode") String preferentialCode,
            @RequestParam("preferentialName") String preferentialName,
            @RequestParam("startDay") String startDay,
            @RequestParam("endDay") String endDay,
            @RequestParam("quantity") int quantity,
            @RequestParam("rate") double rate,
            @RequestParam("preferentialDescription") String preferentialDescription,
            @RequestParam(value = "preferentialImg", required = false) MultipartFile preferentialImg,
            @RequestParam("employeeID") String employeeID,
            Model model) {

        Preferential preferential = new Preferential();
        preferential.setPreferentialCode(preferentialCode);
        preferential.setPreferentialName(preferentialName);
        preferential.setStartDay(startDay);
        preferential.setEndDay(endDay);
        preferential.setQuantity(quantity);
        preferential.setRate(rate);
        preferential.setPreferentialDescription(preferentialDescription);
        preferential.setEmployeeID(employeeID);

        if (preferentialImg != null && !preferentialImg.isEmpty()) {
            try {
                BufferedImage originalImage = ImageIO.read(preferentialImg.getInputStream());
                if (originalImage == null) {
                    model.addAttribute("error", "Invalid image file");
                    return "preferential/preferential_add";
                }

                Graphics2D graphics = originalImage.createGraphics();
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Font font = new Font("Arial", Font.BOLD, 300    );
                graphics.setFont(font);
                graphics.setColor(Color.RED);
                String rateText = "" + rate;
                FontMetrics fontMetrics = graphics.getFontMetrics();
                int textWidth = fontMetrics.stringWidth(rateText);
                int x = (originalImage.getWidth() - textWidth) / 2;
                int y = originalImage.getHeight() - 50;
                graphics.drawString(rateText, x, y);
                graphics.dispose();

                String outputFileName = "output_" + System.currentTimeMillis() + ".png";
                File outputFile = new File(outputDirectory, outputFileName);
                ImageIO.write(originalImage, "png", outputFile);

                preferential.setPreferentialImg(outputFileName);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "File upload failed");
                return "preferential/preferential_add";
            }
        }

        preferentialService.savePreferential(preferential);
        return "redirect:/preferentials";
    }
    // Handle deletion
    @GetMapping("/preferential/delete/{preferentialCode}")
    public String deletePreferential(@PathVariable("preferentialCode") String preferentialCode, Model model) {
        Optional<Preferential> preferentialOpt = preferentialService.getPreferentialsByCode(preferentialCode);

        if (preferentialOpt.isPresent()) {
            preferentialService.deletePreferential(preferentialCode); // Call the service to delete
            return "redirect:/preferentials"; // Redirect to the list after deletion
        } else {
            model.addAttribute("error", "Preferential not found");
            return "preferential/preferentials"; // Redirect to the list if not found
        }
    }

}
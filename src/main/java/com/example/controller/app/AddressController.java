package com.example.controller.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String BASE_URL = "https://provinces.open-api.vn/api";

    @GetMapping("/provinces")
    public ResponseEntity<?> getProvinces() {
        String url = BASE_URL + "/?depth=1";
        return ResponseEntity.ok(restTemplate.getForObject(url, Object.class));
    }

    @GetMapping("/districts/{provinceCode}")
    public ResponseEntity<?> getDistricts(@PathVariable String provinceCode) {
        String url = BASE_URL + "/p/" + provinceCode + "?depth=2";
        return ResponseEntity.ok(restTemplate.getForObject(url, Object.class));
    }

    @GetMapping("/wards/{districtCode}")
    public ResponseEntity<?> getWards(@PathVariable String districtCode) {
        String url = BASE_URL + "/d/" + districtCode + "?depth=2";
        return ResponseEntity.ok(restTemplate.getForObject(url, Object.class));
    }
}


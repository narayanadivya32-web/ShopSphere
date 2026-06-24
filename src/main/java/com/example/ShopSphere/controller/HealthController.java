package com.example.ShopSphere.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api")
@RestController
public class HealthController {

    @GetMapping("/health")
   public  Map<String,String> health(){
        return Map.of(
                "Status", "UP",
                "message","Sophspere backend running succesfully"
        );
    }
}

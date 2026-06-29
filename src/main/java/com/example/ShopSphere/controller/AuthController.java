package com.example.ShopSphere.controller;

import com.example.ShopSphere.dto.LoginRequest;
import com.example.ShopSphere.dto.RegisterRequest;
import com.example.ShopSphere.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
        @Valid @RequestBody RegisterRequest request
            ){
        userService.registerUser(request);
        return ResponseEntity.ok("user Registration succesfull");
    }

@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest login){
       String token =  userService.LoginUser(login);
        return ResponseEntity.ok(token);
}




}

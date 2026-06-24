package com.example.ShopSphere.service;

import com.example.ShopSphere.dto.LoginRequest;
import com.example.ShopSphere.dto.RegisterRequest;

public interface UserService {

    void registerUser(RegisterRequest registerRequest);



    void LoginUser(LoginRequest login);
}

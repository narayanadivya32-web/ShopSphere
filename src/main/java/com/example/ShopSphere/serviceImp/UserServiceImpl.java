package com.example.ShopSphere.serviceImp;

import com.example.ShopSphere.dto.LoginRequest;
import com.example.ShopSphere.dto.RegisterRequest;
import com.example.ShopSphere.entity.User;
import com.example.ShopSphere.enums.Role;
import com.example.ShopSphere.repository.UserRepository;
import com.example.ShopSphere.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService {

private final UserRepository userRepository;

private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void registerUser(RegisterRequest registerRequest){


        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new RuntimeException("email already exist");
        }

        User user=new User();

       user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        //user.setPassword(PasswordEncoder.encode(registerRequest.getPassword());

       user.setPassword((passwordEncoder.encode(registerRequest.getPassword())));
        user.setRole(Role.USER);

        userRepository.save(user);
    }
@Override
public void LoginUser(LoginRequest login){

        User user=userRepository.findByEmail(login.getEmail()).orElseThrow(()->new RuntimeException("user not found"));

        if(!passwordEncoder.matches(login.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }

    }


}

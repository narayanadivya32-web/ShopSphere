package com.example.ShopSphere.serviceImp;

import com.example.ShopSphere.dto.LoginRequest;
import com.example.ShopSphere.dto.RegisterRequest;
import com.example.ShopSphere.entity.User;
import com.example.ShopSphere.enums.Role;
import com.example.ShopSphere.repository.UserRepository;
import com.example.ShopSphere.security.JwtService;
import com.example.ShopSphere.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService {

private final UserRepository userRepository;

private final PasswordEncoder passwordEncoder;

private final JwtService jwtService;

private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtService jwtService, AuthenticationManager authenticationManager){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
        this.authenticationManager=authenticationManager;
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
public String LoginUser(LoginRequest login){
// manual password verification
      /*  User user=userRepository.findByEmail(login.getEmail()).orElseThrow(()->new RuntimeException("user not found"));

        if(!passwordEncoder.matches(login.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }

        return jwtService.generateToken(user.getEmail());
*/

    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    login.getEmail(),
                    login.getPassword()
            )

    );

    User user = userRepository.findByEmail(login.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    return  jwtService.generateToken(user);
    }


}

package com.appointment_app.service;


import com.appointment_app.dto.AuthRequest;
import com.appointment_app.dto.AuthResponse;
import com.appointment_app.dto.UserDto;
import com.appointment_app.entity.User;
import com.appointment_app.model.CustomUserDetails;
import com.appointment_app.repository.UserRepository;
import com.appointment_app.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UserDto register(User user) {
        user.setPassword(bCryptPasswordEncoder
                .encode(user.getPassword()));
        user.setUniqueId(generatePatientId());


       User savedUser= userRepository.save(user);
       UserDto userDto=new UserDto();
       userDto.setId(savedUser.getId());
       userDto.setEmail(savedUser.getEmail());
       userDto.setUserType(savedUser.getUserType());
       userDto.setUsername(savedUser.getUsername());
       userDto.setMobile(savedUser.getMobile());
       userDto.setUniqueId(savedUser.getUniqueId());
       return userDto ;
    }


    public AuthResponse verify(AuthRequest request) {
        Authentication authenticate
                = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), request.password()
                )
        );
               AuthResponse authResponse=new AuthResponse(request.username(),jwtService.generateToken(request),"1");
        //var u = userRepository.findByUserName(user.getUserName());
        if(authenticate.isAuthenticated())
            return authResponse;
        return new AuthResponse(request.username(),"Invalid Request!","0");
    }


    private Long generatePatientId() {
        // Start from 1000000
        Optional<User> last = userRepository.findTopByOrderByIdDesc();
        long nextId  = new Random().nextLong(900000) + 100000;
        System.out.println("number is "+nextId);
        return nextId;
    }

}

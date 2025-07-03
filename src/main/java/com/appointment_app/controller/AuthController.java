package com.appointment_app.controller;

import com.appointment_app.dto.AuthRequest;
import com.appointment_app.dto.AuthResponse;
import com.appointment_app.dto.UserDto;
import com.appointment_app.entity.User;
import com.appointment_app.repository.UserRepository;
import com.appointment_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserRepository userRepository;
    private final UserService userService;
    public AuthController(UserRepository userRepository,UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(userService.verify(request));
    }


    @PostMapping("/signup")
    public  ResponseEntity<UserDto> register(@RequestBody User user){
       return ResponseEntity.ok(userService.register(user));

    }


}

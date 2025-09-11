package com.assessment.webApplicationBackend.controller;

import com.assessment.webApplicationBackend.dto.userDto;
import com.assessment.webApplicationBackend.service.impl.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "*")
public class userController {

    @Autowired
    private userService userService;

    @PostMapping("/register")
    public ResponseEntity<userDto> register(@RequestBody userDto userDto){
        return userService.register(userDto);
    }
    @PostMapping("/login")
    public ResponseEntity<userDto> login(@RequestBody userDto userDto){
        return userService.Login(userDto);
    }
}

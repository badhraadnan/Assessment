package com.assessment.webApplicationBackend.service.impl;

import com.assessment.webApplicationBackend.dto.userDto;
import com.assessment.webApplicationBackend.entity.User;
import com.assessment.webApplicationBackend.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userService {

    @Autowired
    private userRepo userRepo;

    public ResponseEntity<userDto> register(userDto dto){
        Optional<User> existUser = userRepo.findByUsername(dto.getUsername());
        if (existUser.isEmpty()){
            User user = userDto.convertToEntity(dto);
            User saveUser = userRepo.save(user);
            return ResponseEntity.ok(userDto.convertToDto(saveUser));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }


    public ResponseEntity<userDto> Login(userDto dto) {
        User user = userRepo.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());

        if (user != null) {
            return ResponseEntity.ok(userDto.convertToDto(user));
        } else {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
    }

}

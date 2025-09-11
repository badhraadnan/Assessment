package com.assessment.webApplicationBackend.dto;

import com.assessment.webApplicationBackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class userDto {

    private Long userId;
    private String username;
    private String password;

    public static User convertToEntity(userDto userDto){
     return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }

    public static userDto convertToDto(User user){
        return userDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

}

package com.example.modulecore.dto;

import com.example.modulecore.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;

    public static UserDto fromEntity(User user) {
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        return userDto;
    }
}
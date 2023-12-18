package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}

package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.BooksDto;
import com.example.demo.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService  {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
    void updateUser(Long id, UserDto userDto);
    User findById(Long id);

    void deleteUser(Long id);
    void removeUserRoles(Long userId);
    List<UserDto> searchUsersByNameOrEmail(String keyword);


}

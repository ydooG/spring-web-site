package com.yab.market.services;

import com.yab.market.dto.SignUpDto;
import com.yab.market.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getAllUsers();

    List<UserDto> getAllUsers(Integer page, Integer size, String sort);

    UserDto getUserById(Long userId);

    UserDto addUser(SignUpDto userData);
}

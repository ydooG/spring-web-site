package com.yab.market.services;

import com.yab.market.dto.SignUpDto;
import com.yab.market.dto.UserDto;
import com.yab.market.models.User;
import com.yab.market.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository repository;

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(repository.findAll());
    }

    @Override
    public List<UserDto> getAllUsers(Integer page, Integer size, String property) {
        Sort sort = Sort.by(property);
        PageRequest request = PageRequest.of(page, size, sort);
        Page<User> pageResult = repository.findAll(request);
        List<User> users = pageResult.getContent();
        return UserDto.from(users);

    }

    @Override
    public UserDto getUserById(Long userId) {
        return UserDto.from(repository.findById(userId).get());
    }

    @Override
    public UserDto addUser(SignUpDto userData) {
        User user = User.builder()
                .name(userData.getName())
                .surname(userData.getSurname())
                .email(userData.getEmail())
                .hashedPassword(userData.getPassword())
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(user);
        return  UserDto.from(user);
    }
}

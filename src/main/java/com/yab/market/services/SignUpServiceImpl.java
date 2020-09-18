package com.yab.market.services;

import com.yab.market.dto.SignUpDto;
import com.yab.market.models.Role;
import com.yab.market.models.State;
import com.yab.market.models.User;
import com.yab.market.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpDto form) {
        User user = User.builder()
                .name(form.getName())
                .surname(form.getSurname())
                .email(form.getEmail())
                .hashedPassword(passwordEncoder.encode(form.getPassword()))
                .createdAt(LocalDateTime.now())
                .state(State.CONFIRMED)
                .role(Role.USER)
                .build();

        repository.save(user);
    }
}

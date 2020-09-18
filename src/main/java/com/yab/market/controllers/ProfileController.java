package com.yab.market.controllers;

import com.yab.market.dto.UserDto;
import com.yab.market.models.User;
import com.yab.market.security.details.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @GetMapping("/profile")
    public String getProfilePage(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User currentUser = userDetails.getUser();
        UserDto currentUserDto = UserDto.from(currentUser);
        model.addAttribute("currentUser", currentUserDto);
        return "profile";
    }
}

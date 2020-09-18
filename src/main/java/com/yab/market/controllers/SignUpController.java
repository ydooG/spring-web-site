package com.yab.market.controllers;

import com.yab.market.dto.SignUpDto;
import com.yab.market.models.Product;
import com.yab.market.repositories.UsersRepository;
import com.yab.market.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("form", new SignUpDto());
        return "sign_up";
    }

    @PostMapping("/signUp")
    public String postSignUp(
            Model model,
            HttpServletRequest request,
            @Valid @ModelAttribute("form") SignUpDto signUpDto,
            BindingResult bindingResult) {

        String url;
        if(bindingResult.hasErrors()) {
            model.addAttribute("form", signUpDto);
            url = "sign_up";
        }
        else {
            signUpService.signUp(signUpDto);
            url = "redirect:" + MvcUriComponentsBuilder.fromMappingName("RC#getRootPage").build();
            try {
                request.login(signUpDto.getEmail(), signUpDto.getPassword());
            } catch (ServletException ex) {
                ex.printStackTrace();
            }
        }
        return url;
    }
}

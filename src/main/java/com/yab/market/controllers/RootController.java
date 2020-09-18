package com.yab.market.controllers;

import com.yab.market.dto.UserDto;
import com.yab.market.models.Product;
import com.yab.market.models.User;
import com.yab.market.security.details.UserDetailsImpl;
import com.yab.market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RootController {

    @Autowired
    ProductsService productsService;

    @GetMapping("/")
    public String getRootPage(Authentication authentication, Model model) {
        if(authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User currentUser = userDetails.getUser();
            UserDto currentUserDto = UserDto.from(currentUser);
            model.addAttribute("currentUser", currentUserDto);
        }
        else {
            model.addAttribute("currentUser", null);
        }

        List<Product> products = productsService.getRSS(5);
        model.addAttribute("products", products);
        return "root";
    }
}

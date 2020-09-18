package com.yab.market.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SingInController {

    @GetMapping("/signIn")
    public String getSignInPage() {
        return "sign_in";
    }
}

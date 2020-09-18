package com.yab.market.controllers;

import com.yab.market.models.Product;
import com.yab.market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class BuyController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/buy")
    public String getBuyPage(HttpServletRequest request, Model model) {
        HashMap<Product, Integer> productsAndAmounts = (HashMap<Product, Integer>) request.getSession().getAttribute("cart-items");
        model.addAttribute("products", productsAndAmounts);
        if (productsAndAmounts == null) {
            model.addAttribute("sum", 0);
        }
        else{
            model.addAttribute("sum", productsService.findTotalPrice(productsAndAmounts));
        }
        return "buy";
    }
}

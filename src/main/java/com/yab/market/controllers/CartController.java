package com.yab.market.controllers;

import com.yab.market.models.Product;
import com.yab.market.services.ProductsService;
import com.yab.market.util.CookieParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;


@Controller
public class CartController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/cart")
    public String getCartPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        HashMap<Product, Integer> productsAndAmounts = (HashMap<Product, Integer>) session.getAttribute("cart-items");
        model.addAttribute("products", productsAndAmounts);
        if (productsAndAmounts == null) {
            model.addAttribute("sum", 0);
        }
        else{
            model.addAttribute("sum", productsService.findTotalPrice(productsAndAmounts));
        }
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam("id") String id,
            Model model,
            HttpServletRequest request) {

    addProductToCart(id, request);

        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("CC#getCartPage").build();
    }

    @PostMapping("/cart/amount")
    public String changeAmount(
            @RequestParam("id") Long id,
            @RequestParam("action") String action,
            HttpServletRequest request) {
        HashMap<Product, Integer> productsAndAmounts = (HashMap<Product, Integer>) request.getSession().getAttribute("cart-items");
        Product currentProduct = productsService.getProductById(id);
        if(action.equals("plus")) {
            int oldValue = productsAndAmounts.get(currentProduct);
            productsAndAmounts.replace(currentProduct, oldValue+1);
        }
        else if(action.equals("minus")) {
            int oldValue = productsAndAmounts.get(currentProduct);
            if(oldValue > 1) {
                productsAndAmounts.replace(currentProduct, oldValue - 1);
            }
        }
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("CC#getCartPage").build();
    }

    private void addProductToCart(String id, HttpServletRequest request) {
        HashMap<Product, Integer> productsAndAmounts;
        Product currProduct = productsService.getProductById(Long.parseLong(id));
        HttpSession session = request.getSession();
        productsAndAmounts = (HashMap<Product, Integer>) session.getAttribute("cart-items");

        if (productsAndAmounts == null) {
            productsAndAmounts = new HashMap<>();
            productsAndAmounts.put(currProduct, 1);
        } else {
            if (productsAndAmounts.containsKey(currProduct)) {
                Integer oldValue = productsAndAmounts.get(currProduct);
                productsAndAmounts.replace(currProduct, oldValue + 1);
            } else {
                productsAndAmounts.put(currProduct, 1);
            }
        }
        session.setAttribute("cart-items", productsAndAmounts);
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam("id") Long id, HttpServletRequest request) {
        Product productToRemove = productsService.getProductById(id);
        HashMap<Product, Integer> productsAndAmounts = (HashMap<Product, Integer>) request.getSession().getAttribute("cart-items");
        productsAndAmounts.remove(productToRemove);
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("CC#getCartPage").build();
    }
}

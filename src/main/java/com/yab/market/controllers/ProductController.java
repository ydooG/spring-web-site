package com.yab.market.controllers;

import com.yab.market.models.Category;
import com.yab.market.models.Product;
import com.yab.market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;


@Controller
public class ProductController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/product/{id}")
    public String getProductPage(@PathVariable("id") Product product,
                                 Model model) {
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/addProduct")
    public String getAddPage(Model model) {
        model.addAttribute("form", new Product());
        model.addAttribute("categories", Category.values());

        return "add_product";
    }

    @PostMapping("/addProduct")
    public String postAddPage(
            Model model,
            @Valid @ModelAttribute("form") Product product,
            BindingResult bindingResult) {
        String url;
        model.addAttribute("categories", Category.values());
        if(bindingResult.hasErrors()) {
            model.addAttribute("form", product);
            url = "add_product";
        }
        else {
            productsService.addProduct(product);
            url = "redirect:" + MvcUriComponentsBuilder.fromMappingName("RC#getRootPage").build();
        }
        return url;
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") Product product, Model model) {
            model.addAttribute("form", product);
            model.addAttribute("categories", Category.values());
        return "product_edit";
    }

    @PostMapping("/product/edit/{id}")
    public String editProductPAge(
            Model model,
            @Valid @ModelAttribute("form") Product product,
            BindingResult bindingResult) {
        String url;
        if(bindingResult.hasErrors()) {
            model.addAttribute("form", product);
            url = "add_product";
        }
        else {
            productsService.updateProduct(product);
            url = "redirect:" + MvcUriComponentsBuilder.fromMappingName("RC#getRootPage").build();
        }
        return url;
    }

    @PostMapping("/product/remove/{id}")
    public String removeProduct(@PathVariable("id") Product product) {
        productsService.removeProduct(product);
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("RC#getRootPage").build();
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productsService.getAllProducts());
        return "products";
    }


}

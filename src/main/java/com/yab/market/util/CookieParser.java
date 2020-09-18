package com.yab.market.util;

import com.yab.market.models.Product;
import com.yab.market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CookieParser {

    @Autowired
    private ProductsService productsService;

    public HashMap<Product, Integer> parseCookie(String cookie) {
        HashMap<Product, Integer> products = new HashMap<>();
        String[] arr = cookie.split("-");
        for (int i = 0; i < arr.length; i++) {
            String idS = arr[i].split(":")[0];
            String amountS = arr[i].split(":")[1];
            Long id = Long.parseLong(idS);
            Integer amount = Integer.parseInt(amountS);
            products.put(productsService.getProductById(id), amount);
        }
        return products;
    }
}

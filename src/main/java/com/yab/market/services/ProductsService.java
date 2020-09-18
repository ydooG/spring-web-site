package com.yab.market.services;


import com.yab.market.dto.UserDto;
import com.yab.market.models.Category;
import com.yab.market.models.Product;

import java.util.List;
import java.util.Map;

public interface ProductsService {
    List<Product> getAllProducts();

    Product getProductById(Long productId);

    void addProduct(Product product);

    List<Product> getAllProductsByCategory(Category category);

    double findTotalPrice(Map<Product, Integer> productsAndAmounts);

    void updateProduct(Product product);

    void removeProduct(Product product);

    List<Product> getRSS(int size);
}

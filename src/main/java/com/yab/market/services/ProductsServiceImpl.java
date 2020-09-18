package com.yab.market.services;

import com.yab.market.dto.UserDto;
import com.yab.market.models.Category;
import com.yab.market.models.Product;
import com.yab.market.models.User;
import com.yab.market.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository repository;

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        return repository.findById(productId).get();
    }

    @Override
    public void addProduct(Product product) {
        product.setAddedAt(LocalDateTime.now());
        product.setLastModified(product.getAddedAt());
        repository.save(product);
    }

    @Override
    public List<Product> getAllProductsByCategory(Category category) {
        return repository.findAllByCategory(category);
    }

    @Override
    public double findTotalPrice(Map<Product, Integer> productsAndAmounts) {
        double sum = 0;
        for (Map.Entry<Product, Integer> entry : productsAndAmounts.entrySet()) {
            sum += entry.getValue() * entry.getKey().getPrice();
        }
        return sum;
    }

    @Override
    public void updateProduct(Product product) {
        product.setLastModified(LocalDateTime.now());
        repository.save(product);
    }

    @Override
    public void removeProduct(Product product) {
        repository.delete(product);
    }

    @Override
    public List<Product> getRSS(int size) {
        List<Product> products = repository.findAll();
        products.sort(Comparator.comparing(Product::getLastModified));
        Collections.reverse(products);
        List<Product> part = products.subList(0, size);
        return part;
    }
}

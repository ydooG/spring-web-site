package com.yab.market.repositories;

import com.yab.market.models.Category;
import com.yab.market.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);
}

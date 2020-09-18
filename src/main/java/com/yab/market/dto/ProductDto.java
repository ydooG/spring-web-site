package com.yab.market.dto;

import com.yab.market.models.Category;
import com.yab.market.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private String name;
    private double price;
    private String description;
    private Category category;
    private LocalDateTime addedAt;
    private LocalDateTime lastModified;

    public static ProductDto from(Product product) {
        return ProductDto.builder()
                .addedAt(product.getAddedAt())
                .category(product.getCategory())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    public static List<ProductDto> from(List<Product> products) {
        return products.stream()
                .map(ProductDto::from)
                .collect(Collectors.toList());
    }
}

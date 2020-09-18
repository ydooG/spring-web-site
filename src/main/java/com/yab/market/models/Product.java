package com.yab.market.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Size(min = 2, max = 255)
    private String name;

    @PositiveOrZero
    private double price;

    @NotEmpty
    @Size(min = 2, max = 512)
    private String description;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    private LocalDateTime addedAt;

    private LocalDateTime lastModified;
}

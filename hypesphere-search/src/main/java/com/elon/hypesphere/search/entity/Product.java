package com.elon.hypesphere.search.entity;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String category;
    private String brand;
    private String description;
    private String images;
    private Double price;
    private Double discount;
}

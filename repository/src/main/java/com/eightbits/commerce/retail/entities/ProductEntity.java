package com.eightbits.commerce.retail.entities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
public class ProductEntity {
    private String id;
    private String name;
    private String description;
    private String gtin;
    private String gln;
    private String brand;
    private String imageUrl;
}

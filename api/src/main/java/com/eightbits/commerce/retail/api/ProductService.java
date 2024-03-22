package com.eightbits.commerce.retail.api;

import com.eightbits.commerce.retail.dto.Product;

public interface ProductService {
    Product createProduct(Product product);

    Product getProduct(String id);

    void deleteProduct(String id);
}

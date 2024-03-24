package com.eightbits.commerce.retail.api;

import com.eightbits.commerce.retail.dto.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product createProduct(Product product);

    Product getProduct(String id);

    void deleteProduct(String id);

    Page<Product> findAll(int page, int size);
}

package com.eightbits.commerce.retail.web.resources;

import com.eightbits.commerce.retail.api.ProductService;
import com.eightbits.commerce.retail.web.generated.v1.api.ProductsApi;
import com.eightbits.commerce.retail.web.generated.v1.model.Product;
import com.eightbits.commerce.retail.web.mappings.ProductMapper;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.eightbits.commerce.retail.web.configuration.HeaderConstants.TOTAL_ELEMENTS;
import static com.eightbits.commerce.retail.web.configuration.HeaderConstants.TOTAL_PAGES;

@RestController
@RequestMapping("/api/v1")
public class ProductsResource implements ProductsApi {

    private final Logger logger;
    private final ProductService service;
    private final ProductMapper mapper;


    public ProductsResource(Logger logger, ProductService service, ProductMapper mapper) {
        this.logger = logger;
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Product> findProductById(UUID id) {
        return ProductsApi.super.findProductById(id);
    }

    @Override
    public ResponseEntity<List<Product>> findProducts(String productName, Integer page, Integer size) {
        logger.info("Getting all users");

        Page<Product> users = service.findAll(page, size).map(mapper::map);

        var headers = new HttpHeaders();
        headers.add(TOTAL_PAGES, String.valueOf(users.getTotalPages()));
        headers.add(TOTAL_ELEMENTS, String.valueOf(users.getTotalElements()));

        return ResponseEntity.ok().headers(headers).body(users.getContent());
    }

    @Override
    public ResponseEntity<Void> remove(UUID id) {
        return ProductsApi.super.remove(id);
    }

    @Override
    public ResponseEntity<Void> saveProduct(Product product) {
        return ProductsApi.super.saveProduct(product);
    }
}

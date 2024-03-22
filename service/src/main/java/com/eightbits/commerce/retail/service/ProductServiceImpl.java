package com.eightbits.commerce.retail.service;

import com.eightbits.commerce.retail.api.ProductService;
import com.eightbits.commerce.retail.dto.Product;
import com.eightbits.commerce.retail.exceptions.ProductNotFoundException;
import com.eightbits.commerce.retail.mappings.ProductMapper;
import com.eightbits.commerce.retail.repository.ProductRepository;
import com.eightbits.commerce.retail.utils.With;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Product createProduct(Product product) {
        return With.value(product).map(mapper::map).map(repository::save).map(mapper::map).get();
    }

    @Override
    public Product getProduct(String id) {
        return repository.findById(id).map(mapper::map).orElseThrow(() ->
                new ProductNotFoundException("Product not found for given id: " + id));
    }

    @Override
    public void deleteProduct(String id) {
        repository.deleteById(id);
    }
}

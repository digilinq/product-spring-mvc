package com.eightbits.commerce.retail.repository;

import com.eightbits.commerce.retail.entities.ProductEntity;
import com.mongodb.client.MongoClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataMongoTest
class ProductRepositoryTest {

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ProductRepository productRepository;

    @Test
    void should_work() {
        assertNotNull(mongoClient);
        assertNotNull(mongoTemplate);
        assertNotNull(productRepository);
    }

    @Test
    void should_save_product() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setName("Computer");
        product.setDescription("Gaming computer");
        // Act
        ProductEntity savedProduct = productRepository.save(product);
        // Assert
        assertNotNull(savedProduct);
        assertNotNull(savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getDescription(), savedProduct.getDescription());
    }
}

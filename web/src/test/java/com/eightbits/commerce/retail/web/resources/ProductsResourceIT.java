package com.eightbits.commerce.retail.web.resources;

import com.eightbits.commerce.retail.entities.ProductEntity;
import com.eightbits.commerce.retail.integration.IntegrationTest;
import com.eightbits.commerce.retail.integration.TestUri;
import com.eightbits.commerce.retail.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@IntegrationTest
class ProductsResourceIT {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:7.0.7"));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void should_work() throws Exception {
        ProductEntity product = new ProductEntity();
        product.setName("Computer");
        product.setDescription("Gaming computer");

        productRepository.save(product);

        Pageable pageable = PageRequest.of(0, 10);
        Page<ProductEntity> all = productRepository.findAll(pageable);

        System.out.printf("%d", all.getNumberOfElements());

        mockMvc.perform(MockMvcRequestBuilders.get(TestUri.ENDPOINT_PRODUCTS)).andDo(MockMvcResultHandlers.print());
    }
}
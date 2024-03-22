package com.eightbits.commerce.retail.mappings;

import com.eightbits.commerce.retail.dto.Product;
import com.eightbits.commerce.retail.entities.ProductEntity;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, implementationName = "ProductMapperImpl")
@AnnotateWith(value = Component.class, elements = @AnnotateWith.Element(strings = "entityMapper"))
public interface ProductMapper {
    Product map(ProductEntity product);
    ProductEntity map(Product product);
}

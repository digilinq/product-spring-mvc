package com.eightbits.commerce.retail.web.mappings;

import com.eightbits.commerce.retail.dto.Product;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, implementationName = "ProductMapperImpl")
@AnnotateWith(value = Component.class, elements = @AnnotateWith.Element(strings = "webMapper"))
public interface ProductMapper {
    com.eightbits.commerce.retail.web.generated.v1.model.Product map(Product product);
    Product map(com.eightbits.commerce.retail.web.generated.v1.model.Product product);
}

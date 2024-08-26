package com.github.io.MateusHCandido.product_service.infrastructure.controller.dto;

import com.github.io.MateusHCandido.product_service.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private String productName;
    private String productCategory;
    private BigDecimal productPrice;
    private String productDescription;

    public ProductResponseDto(Product entity){
        this.productName = entity.getProductName();
        this.productPrice = entity.getProductPrice();
        this.productCategory = entity.getProductCategory().name();
        this.productDescription = entity.getProductDescription();
    }
}

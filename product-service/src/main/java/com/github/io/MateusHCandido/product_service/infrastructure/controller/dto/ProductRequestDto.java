package com.github.io.MateusHCandido.product_service.infrastructure.controller.dto;



import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = "productName cannot be blank")
    private String productName;
    @NotBlank(message = "productCategory cannot be blank")
    private String productCategory;
    @NotNull(message = "productDescription cannot be null")
    private BigDecimal productPrice;
    @NotBlank(message = "productDescription cannot be blank")
    private String productDescription;

    public Product toProduct(){
        return new Product(productName, getProductCategory(productCategory), productPrice, productDescription);
    }

    protected ProductCategory getProductCategory(String category) {
        if (category == null) {
            throw new IllegalArgumentException("product category cannot be null");
        }

        switch (category.trim().toUpperCase()) {
            case "FOOD":
                return ProductCategory.FOOD;
            case "BEVERAGE":
                return ProductCategory.BEVERAGE;
            case "AUTOMOTIVE":
                return ProductCategory.AUTOMOTIVE;
            case "TOY":
                return ProductCategory.TOY;
            case "KITCHEN":
                return ProductCategory.KITCHEN;
            case "ELECTRONIC":
                return ProductCategory.ELECTRONIC;
            case "SPORT":
                return ProductCategory.SPORT;
            case "LEISURE":
                return ProductCategory.LEISURE;
            case "MOVIES":
                return ProductCategory.MOVIES;
            case "GAMES":
                return ProductCategory.GAMES;
            case "BOOKS":
                return ProductCategory.BOOKS;
            case "PET":
                return ProductCategory.PET;
            case "CLOTHING":
                return ProductCategory.CLOTHING;
            default:
                throw new IllegalArgumentException("informed product category not exist");
        }
    }
}

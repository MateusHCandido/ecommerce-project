package com.github.io.MateusHCandido.product_service.application.usecase.product;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductAlreadyRegisteredException;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;

import java.math.BigDecimal;

public class SaveProductUseCase {

    private final ProductDomainRepository repository;

    public SaveProductUseCase(ProductDomainRepository repository) {
        this.repository = repository;
    }

    public Product saveProduct(String name, String category, Double price, String description){
        ProductCategory productCategory = getProductCategory(category);

        boolean productAlreadyRegistered = repository.existsByProductNameAndProductCategory(name, productCategory);
        if(productAlreadyRegistered) throw new ProductAlreadyRegisteredException("product with name and category entered are already registered");

        Product product = new Product(name, productCategory, new BigDecimal(price), description);

        return repository.saveProduct(product);
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

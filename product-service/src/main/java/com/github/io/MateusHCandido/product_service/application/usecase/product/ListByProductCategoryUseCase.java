package com.github.io.MateusHCandido.product_service.application.usecase.product;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;

import java.util.List;

public class ListByProductCategoryUseCase {

    private final ProductDomainRepository repository;

    public ListByProductCategoryUseCase(ProductDomainRepository repository) {
        this.repository = repository;
    }

    public List<Product> listByProductCategory(String productCategory){
        ProductCategory category = getProductCategory(productCategory);

        return repository.listByProductCategory(category);
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

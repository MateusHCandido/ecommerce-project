package com.github.io.MateusHCandido.product_service.application.usecase.product;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductNotFoundException;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import com.github.io.MateusHCandido.product_service.domain.exception.BlankFieldException;
import com.github.io.MateusHCandido.product_service.domain.exception.ProductPriceException;

import java.math.BigDecimal;
import java.util.Optional;

public class UpdateProductPriceUseCase {

    private final ProductDomainRepository repository;

    public UpdateProductPriceUseCase(ProductDomainRepository repository) {
        this.repository = repository;
    }


    public Product updateProductPrice(String productName, String productCategory, BigDecimal productPrice){
        verifyProductName(productName);
        verifyProductPrice(productPrice);
        ProductCategory category = getProductCategory(productCategory);

        Optional<Product> product = repository
                .findByProductName(productName)
                .stream()
                .filter(filteredProduct -> filteredProduct.getProductCategory().equals(category))
                .findFirst();


        if (!product.isPresent()) throw new ProductNotFoundException("product not found");

        product.get().setProductPrice(productPrice);

        return product.get();
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

    protected static void verifyProductPrice(BigDecimal productPrice){
        if (productPrice == null) throw new ProductPriceException("the product price cannot be null");

        Double price = productPrice.doubleValue();
        boolean isNegative = price < 0d;
        boolean isEqualZero = price == 0d;

        if (isEqualZero) throw new ProductPriceException("the product price cannot be zero");
        if (isNegative) throw new ProductPriceException("the product price cannot be negative");
    }

    protected static void verifyProductName(String productName){
        if(productName.equals(null)) throw new NullPointerException("product name cannot be null");
        productName = productName.trim();
        if(productName.isBlank()) throw new BlankFieldException("product name cannot be blank");
    }
}

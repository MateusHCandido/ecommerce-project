package com.github.io.MateusHCandido.product_service.application.usecase.product;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.exception.BlankFieldException;

import java.util.List;

public class FindByProductNameUseCase {

    private final ProductDomainRepository repository;

    public FindByProductNameUseCase(ProductDomainRepository repository) {
        this.repository = repository;
    }

    public List<Product> findByProductName(String productName){
        verifyProductName(productName);

        return repository.findByProductName(productName);
    }

    protected static void verifyProductName(String productName){
        if(productName.equals(null)) throw new NullPointerException("product name cannot be null");
        productName = productName.trim();
        if(productName.isBlank()) throw new BlankFieldException("product name cannot be blank");
    }
}

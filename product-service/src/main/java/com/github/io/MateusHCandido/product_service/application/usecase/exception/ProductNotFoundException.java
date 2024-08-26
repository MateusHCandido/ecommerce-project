package com.github.io.MateusHCandido.product_service.application.usecase.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productNotFound) {
        super(productNotFound);
    }
}

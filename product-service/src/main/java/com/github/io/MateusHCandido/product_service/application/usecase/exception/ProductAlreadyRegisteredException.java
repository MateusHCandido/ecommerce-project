package com.github.io.MateusHCandido.product_service.application.usecase.exception;

public class ProductAlreadyRegisteredException extends RuntimeException {
    public ProductAlreadyRegisteredException(String productAlreadyRegisteredException) {
        super(productAlreadyRegisteredException);
    }
}

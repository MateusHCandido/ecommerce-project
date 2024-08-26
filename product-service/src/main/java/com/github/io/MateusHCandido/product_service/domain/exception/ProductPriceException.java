package com.github.io.MateusHCandido.product_service.domain.exception;

public class ProductPriceException extends RuntimeException {
    public ProductPriceException(String productPriceException) {
        super(productPriceException);
    }
}

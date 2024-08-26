package com.github.io.MateusHCandido.product_service.domain;


import java.time.LocalDateTime;

public class ProductStock {

    private Product product;
    private Long quantityForStock;
    private LocalDateTime launchTime;

    public ProductStock(){}

    public ProductStock(Product product, Long quantityForStock) {
        this.product = product;
        this.quantityForStock = quantityForStock;
        this.launchTime = LocalDateTime.now();
    }


    public Long getQuantityForStock() {
        return quantityForStock;
    }

    public LocalDateTime getLaunchTime() {
        return launchTime;
    }

    public Product getProduct() {
        return product;
    }
}

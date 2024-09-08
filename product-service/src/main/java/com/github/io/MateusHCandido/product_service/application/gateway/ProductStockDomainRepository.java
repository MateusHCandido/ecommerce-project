package com.github.io.MateusHCandido.product_service.application.gateway;

import com.github.io.MateusHCandido.product_service.domain.ProductStock;

public interface ProductStockDomainRepository {


    void stockProduct(ProductStock productStock);

    void reduceStock(ProductStock productStock);
}

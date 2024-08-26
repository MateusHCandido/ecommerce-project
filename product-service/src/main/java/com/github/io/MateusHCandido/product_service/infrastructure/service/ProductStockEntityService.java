package com.github.io.MateusHCandido.product_service.infrastructure.service;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductStockDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductNotFoundException;
import com.github.io.MateusHCandido.product_service.domain.ProductStock;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import com.github.io.MateusHCandido.product_service.infrastructure.persistence.entity.ProductEntity;
import com.github.io.MateusHCandido.product_service.infrastructure.persistence.entity.ProductStockEntity;
import com.github.io.MateusHCandido.product_service.infrastructure.persistence.repository.ProductEntityRepository;
import com.github.io.MateusHCandido.product_service.infrastructure.persistence.repository.ProductStockEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductStockEntityService implements ProductStockDomainRepository {

    @Autowired
    private ProductEntityRepository productEntityRepository;

    @Autowired
    private ProductStockEntityRepository productStockEntityRepository;

    @Override
    public void stockProduct(ProductStock productStock) {
        String productName = productStock.getProduct().getProductName();
        ProductCategory productCategory = productStock.getProduct().getProductCategory();
        Long productQuantity = productStock.getQuantityForStock();

        ProductEntity productEntity = productEntityRepository.findByProductNameAndProductCategory(productName, productCategory);

        if (productEntity == null) throw new ProductNotFoundException("product not found");

        productStockEntityRepository.save(new ProductStockEntity(productEntity, productQuantity));
    }
}

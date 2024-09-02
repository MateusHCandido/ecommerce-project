package com.io.github.MateusHCandido.inventory_service.service;

import com.io.github.MateusHCandido.inventory_service.entity.ProductInventoryEntity;
import com.io.github.MateusHCandido.inventory_service.entity.domain.ProductStock;
import com.io.github.MateusHCandido.inventory_service.repository.ProductInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductInventoryService {

    @Autowired
    private ProductInventoryRepository repository;

    public String increaseProductInventory(ProductStock productStock){
        Optional<ProductInventoryEntity> entity = repository
                .findByProductNameAndProductCategory(productStock.getProduct().getProductName(),
                                                    productStock.getProduct().getProductCategory());

        if (entity.isEmpty()) {
            entity = convertToEntity(productStock);
            entity.get().increaseInventory(productStock.getQuantityForStock());
        }


        repository.save(entity.get());

        return "product inventory successfully increased";
    }

    public String reduceProductInventory(ProductStock productStock){
        Optional<ProductInventoryEntity> entity = repository
                .findByProductNameAndProductCategory(productStock.getProduct().getProductName(),
                        productStock.getProduct().getProductCategory());

        if (entity.isEmpty()) {
            return "product not found";
        }

        entity.get().reduceInventory(productStock.getQuantityForStock());

        repository.save(entity.get());

        return "product inventory successfully reduced";
    }

    protected Optional<ProductInventoryEntity> convertToEntity(ProductStock productStock){
        ProductInventoryEntity entity = new ProductInventoryEntity();
        entity.setProductName(productStock.getProduct().getProductName());
        entity.setProductPrice(productStock.getProduct().getProductPrice());
        entity.updateDescription(productStock.getProduct().getProductDescription());

        return Optional.of(entity);
    }
}

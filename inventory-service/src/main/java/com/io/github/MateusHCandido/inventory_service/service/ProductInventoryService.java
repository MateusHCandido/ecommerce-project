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

    public void increaseProductInventory(ProductStock productStock){
        System.out.println(productStock.getProduct().getProductCategory());
        Optional<ProductInventoryEntity> entity = repository
                .findByProductNameAndProductCategory(productStock.getProduct().getProductName(),
                                                    productStock.getProduct().getProductCategory());

        if (entity.isEmpty()) {
            ProductInventoryEntity saveEntity = convertToEntity(productStock);
            repository.save(saveEntity);
        }else{
            entity.get().increaseInventory(productStock.getQuantityForStock());
            repository.save(entity.get());
            System.out.println("Caiu aqui");
        }
    }

    public void reduceProductInventory(ProductStock productStock){
        Optional<ProductInventoryEntity> entity = repository
                .findByProductNameAndProductCategory(productStock.getProduct().getProductName(),
                        productStock.getProduct().getProductCategory());

        if (entity.isEmpty()) throw new IllegalArgumentException("inventory for product not found");

        entity.get().reduceInventory(productStock.getQuantityForStock());

        repository.save(entity.get());
    }

    protected ProductInventoryEntity convertToEntity(ProductStock productStock){
        ProductInventoryEntity entity = new ProductInventoryEntity();
        entity.setProductName(productStock.getProduct().getProductName());
        entity.setProductPrice(productStock.getProduct().getProductPrice());
        entity.setQuantityForStock(productStock.getQuantityForStock());
        entity.setProductDescription(productStock.getProduct().getProductDescription());
        entity.setProductCategory(productStock.getProduct().getProductCategory());
        return entity;
    }
}

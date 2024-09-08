package com.io.github.MateusHCandido.inventory_service.repository;

import com.io.github.MateusHCandido.inventory_service.entity.ProductInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductInventoryRepository extends JpaRepository<ProductInventoryEntity, Long> {
    Optional<ProductInventoryEntity> findByProductNameAndProductCategory(String productName, String productCategory);
}

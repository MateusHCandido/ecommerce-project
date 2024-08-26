package com.github.io.MateusHCandido.product_service.infrastructure.persistence.repository;

import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import com.github.io.MateusHCandido.product_service.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductEntityRepository extends MongoRepository<ProductEntity, String> {
    boolean existsByProductNameAndProductCategory(String productName, ProductCategory productCategory);

    List<ProductEntity> findByProductName(String productName);

    List<ProductEntity> findByProductCategory(ProductCategory productCategory);

    ProductEntity findByProductNameAndProductCategory(String productName, ProductCategory productCategory);
}

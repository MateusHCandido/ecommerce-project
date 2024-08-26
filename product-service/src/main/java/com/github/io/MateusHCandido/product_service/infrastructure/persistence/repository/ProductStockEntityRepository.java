package com.github.io.MateusHCandido.product_service.infrastructure.persistence.repository;

import com.github.io.MateusHCandido.product_service.infrastructure.persistence.entity.ProductStockEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductStockEntityRepository extends MongoRepository<ProductStockEntity, String> {
}

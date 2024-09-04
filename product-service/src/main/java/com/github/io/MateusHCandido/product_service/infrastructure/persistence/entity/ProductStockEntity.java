package com.github.io.MateusHCandido.product_service.infrastructure.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product_stock")
public class ProductStockEntity {

    @Id
    private String productStockId;
    @DBRef
    private ProductEntity product;
    private Long productQuantity;
    private LocalDateTime launchTime;

    public ProductStockEntity (ProductEntity productEntity, Long productQuantity){
        this.product = productEntity;
        this.productQuantity = productQuantity;
    }

    public void increaseProductQuantity(Long productQuantity){
        this.productQuantity += productQuantity;
    }

    public void reduceProductQuantity(Long productQuantity){
        this.productQuantity -= productQuantity;
    }
}

package com.github.io.MateusHCandido.product_service.infrastructure.persistence.entity;

import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection  = "product")
public class ProductEntity {

    @Id
    private String productId;
    private String productName;
    private ProductCategory productCategory;
    private BigDecimal productPrice;
    private String productDescription;

    public ProductEntity(Product product) {
        this.productName = product.getProductName();
        this.productCategory = product.getProductCategory();
        this.productPrice = product.getProductPrice();
        this.productDescription = product.getProductDescription();
    }

    public Product toProduct() {
        return new Product(this.productName, this.productCategory, this.productPrice, this.productDescription);
    }
}

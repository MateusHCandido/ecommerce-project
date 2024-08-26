package com.github.io.MateusHCandido.product_service.application.gateway;

import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDomainRepository {

    Product saveProduct(Product product);

    Product updateProductPrice(String productName, ProductCategory productCategory, BigDecimal productPrice);

    List<Product> listProduct();

    List<Product> findByProductName(String productName);

    Boolean existsByProductNameAndProductCategory(String productName, ProductCategory productCategory);

    List<Product> listByProductCategory(ProductCategory productCategory);

}

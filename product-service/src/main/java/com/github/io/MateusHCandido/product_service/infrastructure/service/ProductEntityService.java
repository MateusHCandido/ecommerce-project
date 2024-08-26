package com.github.io.MateusHCandido.product_service.infrastructure.service;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductAlreadyRegisteredException;
import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductNotFoundException;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.github.io.MateusHCandido.product_service.infrastructure.persistence.entity.ProductEntity;
import com.github.io.MateusHCandido.product_service.infrastructure.persistence.repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductEntityService implements ProductDomainRepository {

    @Autowired
    private ProductEntityRepository repository;
    @Override
    public Product saveProduct(Product product) {
        boolean productAlreadyExist = existsByProductNameAndProductCategory(product.getProductName(), product.getProductCategory());

        if (productAlreadyExist) throw new ProductAlreadyRegisteredException("product already exist");

        ProductEntity entity = new ProductEntity(product);
        repository.save(entity);

        return product;
    }

    @Override
    public Product updateProductPrice(String productName, ProductCategory productCategory, BigDecimal productPrice) {
        boolean productAlreadyExist = existsByProductNameAndProductCategory(productName, productCategory);
        if (productAlreadyExist) throw new ProductAlreadyRegisteredException("product already exist");

        ProductEntity entity = repository.findByProductNameAndProductCategory(productName, productCategory);

        if (entity == null) throw new ProductNotFoundException("product not found");

        entity.setProductPrice(productPrice);
        return entity.toProduct();
    }

    @Override
    public List<Product> listProduct() {
        return repository.findAll()
                .stream()
                .map(product -> product.toProduct())
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByProductName(String productName) {
        return repository.findByProductName(productName)
                .stream()
                .map(product -> product.toProduct())
                .collect(Collectors.toList());
    }

    @Override
    public Boolean existsByProductNameAndProductCategory(String productName, ProductCategory productCategory) {
        boolean productAlreadyExist = repository.existsByProductNameAndProductCategory(productName, productCategory);
        if (productAlreadyExist) throw new ProductAlreadyRegisteredException("product already exist");
        return false;
    }

    @Override
    public List<Product> listByProductCategory(ProductCategory productCategory) {
        return repository.findByProductCategory(productCategory)
                .stream()
                .map(product -> product.toProduct())
                .collect(Collectors.toList());
    }

}

package com.github.io.MateusHCandido.product_service.application.usecase.product;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.domain.Product;

import java.util.List;

public class ListProductUseCase {
    private final ProductDomainRepository repository;

    public ListProductUseCase(ProductDomainRepository repository) {
        this.repository = repository;
    }

    public List<Product> listAll(){
        return repository.listProduct();
    }
}

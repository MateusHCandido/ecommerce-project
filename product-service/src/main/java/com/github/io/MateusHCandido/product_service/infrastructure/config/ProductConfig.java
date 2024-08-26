package com.github.io.MateusHCandido.product_service.infrastructure.config;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.product.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public FindByProductNameUseCase findByProductNameUseCase(ProductDomainRepository repository){
        return new FindByProductNameUseCase(repository);
    }

    @Bean
    public ListByProductCategoryUseCase listByProductCategoryUseCase(ProductDomainRepository repository){
        return new ListByProductCategoryUseCase(repository);
    }

    @Bean
    public ListProductUseCase listProductUseCase(ProductDomainRepository repository){
        return new ListProductUseCase(repository);
    }

    @Bean
    public UpdateProductPriceUseCase updateProductPriceUseCase(ProductDomainRepository repository){
        return new UpdateProductPriceUseCase(repository);
    }

    @Bean
    public SaveProductUseCase saveProductUseCase(ProductDomainRepository repository){
        return new SaveProductUseCase(repository);
    }
}

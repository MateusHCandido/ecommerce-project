package com.github.io.MateusHCandido.product_service.infrastructure.config;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.gateway.ProductStockDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.productStock.StockProductUseCase;
import com.github.io.MateusHCandido.product_service.message.KafkaProducerNewProductStock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockProductConfig {

    @Bean
    public StockProductUseCase stockProductUseCase(ProductStockDomainRepository productStockRepository,
                                                   ProductDomainRepository productRepository,
                                                   KafkaProducerNewProductStock kafkaProducer){
        return new StockProductUseCase(productStockRepository, productRepository, kafkaProducer);
    }
}

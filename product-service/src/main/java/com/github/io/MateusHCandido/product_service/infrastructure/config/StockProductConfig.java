package com.github.io.MateusHCandido.product_service.infrastructure.config;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.gateway.ProductStockDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.productStock.ReduceStockProductUseCase;
import com.github.io.MateusHCandido.product_service.application.usecase.productStock.StockProductUseCase;
import com.github.io.MateusHCandido.product_service.message.KafkaProducerNewProductStock;
import com.github.io.MateusHCandido.product_service.message.KafkaProducerReduceProductStock;
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

    @Bean
    ReduceStockProductUseCase reduceStockProductUseCase(ProductStockDomainRepository productStockRepository,
                                                        ProductDomainRepository productRepository,
                                                        KafkaProducerReduceProductStock kafkaProducer){
        return new ReduceStockProductUseCase(productStockRepository, productRepository, kafkaProducer);
    }
}

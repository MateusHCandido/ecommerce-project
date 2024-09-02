package com.github.io.MateusHCandido.product_service.message;

import com.github.io.MateusHCandido.product_service.domain.ProductStock;
import com.github.io.MateusHCandido.product_service.message.exception.FailMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducerReduceProductStock {

    private static String REDUCE_PRODUCT_STOCK = "REDUCE_PRODUCT_STOCK";

    @Autowired
    private final KafkaTemplate<String, ProductStock> productStockKafkaTemplate;

    public KafkaProducerReduceProductStock(KafkaTemplate<String, ProductStock> productStockKafkaTemplate) {
        this.productStockKafkaTemplate = productStockKafkaTemplate;
    }

    public void sendMessage(ProductStock productStock){
        try{
            productStockKafkaTemplate.send(REDUCE_PRODUCT_STOCK, productStock);
        }catch (RuntimeException exception){
            new FailMessageException(exception.getMessage());
        }
    }
}

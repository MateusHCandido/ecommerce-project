package com.github.io.MateusHCandido.product_service.message;

import com.github.io.MateusHCandido.product_service.domain.ProductStock;
import com.github.io.MateusHCandido.product_service.message.exception.FailMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class KafkaProducerNewProductStock {

    private static String NEW_PRODUCT_STOCK = "NEW_PRODUCT_STOCK";
    @Autowired
    private final KafkaTemplate<String, ProductStock> productStockKafkaTemplate;

    public KafkaProducerNewProductStock(KafkaTemplate<String, ProductStock> productStockKafkaTemplate) {
        this.productStockKafkaTemplate = productStockKafkaTemplate;
    }

    public void sendMessage(ProductStock productStock){
        try{
            productStockKafkaTemplate.send(NEW_PRODUCT_STOCK, productStock);
        }catch (RuntimeException exception){
            new FailMessageException(exception.getMessage());
        }
    }
}

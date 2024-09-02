package com.io.github.MateusHCandido.inventory_service.message;

import com.io.github.MateusHCandido.inventory_service.entity.domain.ProductStock;
import com.io.github.MateusHCandido.inventory_service.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductInventoryConsumerConfig {

    @Autowired
    private ProductInventoryService productInventoryService;

    @KafkaListener(topics = "NEW_PRODUCT_STOCK", groupId = "NEW_PRODUCT_STOCK_GROUP")
    public void listeningForNewProductStock(ProductStock productStock){
        productInventoryService.increaseProductInventory(productStock);
    }

    @KafkaListener(topics = "REDUCE_PRODUCT_STOCK", groupId = "REDUCE_PRODUCT_STOCK_GROUP")
    public void listeningForNewReduceOfStock(ProductStock productStock){
        productInventoryService.reduceProductInventory(productStock);
    }
}

package com.io.github.MateusHCandido.inventory_service.message;

import com.io.github.MateusHCandido.inventory_service.entity.domain.ProductStock;
import com.io.github.MateusHCandido.inventory_service.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaReduceProductStockConsumer {
    @Autowired
    private ProductInventoryService productInventoryService;

    @KafkaListener(topics = "REDUCE_PRODUCT_STOCK", groupId = "REDUCE_PRODUCT_STOCK_GROUP")
    public void listeningForNewReduceOfStock(ProductStock productStock){
        productInventoryService.reduceProductInventory(productStock);
    }
}

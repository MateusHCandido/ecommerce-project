package com.io.github.MateusHCandido.inventory_service.message;

import com.io.github.MateusHCandido.inventory_service.entity.domain.ProductStock;
import com.io.github.MateusHCandido.inventory_service.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaReduceProductStockConsumer {
    @Autowired
    private ProductInventoryService productInventoryService;

    @KafkaListener(topics = "REDUCE_PRODUCT_STOCK", groupId = "REDUCE_PRODUCT_STOCK_GROUP")
    public void consumeReduceProductStock(ProductStock productStock){
        System.out.println("MENSAGEM RECEBIDA");
        productInventoryService.reduceProductInventory(productStock);
    }
}

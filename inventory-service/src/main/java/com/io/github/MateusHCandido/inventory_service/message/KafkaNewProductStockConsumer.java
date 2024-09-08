package com.io.github.MateusHCandido.inventory_service.message;

import com.io.github.MateusHCandido.inventory_service.entity.domain.ProductStock;
import com.io.github.MateusHCandido.inventory_service.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaNewProductStockConsumer {

    @Autowired
    private ProductInventoryService productInventoryService;

    @KafkaListener(topics = "NEW_PRODUCT_STOCK", groupId = "NEW_PRODUCT_STOCK_GROUP")
    public void consumeNewProductForStock(ProductStock productStock){
        System.out.println("MENSAGEM RECEBIDA");
        productInventoryService.increaseProductInventory(productStock);
    }
}

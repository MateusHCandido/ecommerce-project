package com.github.io.MateusHCandido.product_service.message;

import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.ProductStock;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class KafkaProducerNewProductStockTest {

    @Test
    void shouldSendMessageToKafka() {
        KafkaTemplate<String, ProductStock> kafkaTemplate = mock(KafkaTemplate.class);
        KafkaProducerNewProductStock producer = new KafkaProducerNewProductStock(kafkaTemplate);

        Product product = new Product("Test", ProductCategory.ELECTRONIC, new BigDecimal(10L), "Test");

        ProductStock productStock = new ProductStock(product, 1000L);

        producer.sendMessage(productStock);

        verify(kafkaTemplate, times(1)).send("NEW_PRODUCT_STOCK", productStock);
    }
}
package com.io.github.MateusHCandido.inventory_service.service;

import com.io.github.MateusHCandido.inventory_service.entity.ProductInventoryEntity;
import com.io.github.MateusHCandido.inventory_service.entity.domain.Product;
import com.io.github.MateusHCandido.inventory_service.entity.domain.ProductStock;
import com.io.github.MateusHCandido.inventory_service.repository.ProductInventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ProductStockServiceTest {
    @Mock
    private ProductInventoryRepository repository;

    @InjectMocks
    private ProductInventoryService service;

    private ProductInventoryEntity productInventoryEntity;

    private String productName;

    private String productCategory;

    private Long quantityForStock;

    private String productDescription;

    private ProductStock productStock;

    private Product product;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        productInventoryEntity = new ProductInventoryEntity(1L, "computer", "ELECTRONIC", new BigDecimal(5000), "gamer computer", 5000L, LocalDateTime.now());
        quantityForStock = 50L;
        productDescription = "gamer computer";
        product = new Product("computer", "ELETRONIC", new BigDecimal(5000), "gamer computer");
        productStock = new ProductStock(product, 500L);
        productName = productStock.getProduct().getProductName();
        productCategory = productStock.getProduct().getProductCategory();
    }


    @Test
    void mustIncreaseProductInventory(){
        given(repository.findByProductNameAndProductCategory(productName, productCategory))
                .willReturn(Optional.of(productInventoryEntity));

        String result = service.increaseProductInventory(productStock);

        verify(repository, times(1)).save(any(ProductInventoryEntity.class));
        verify(repository, times(1)).findByProductNameAndProductCategory(productName, productCategory);
        assertEquals("product inventory successfully increased", result);
    }

    @Test
    void mustReduceProductInventory(){
        given(repository.findByProductNameAndProductCategory(productName, productCategory))
                .willReturn(Optional.of(productInventoryEntity));

        String result = service.reduceProductInventory(productStock);

        verify(repository, times(1)).save(any(ProductInventoryEntity.class));
        verify(repository, times(1)).findByProductNameAndProductCategory(productName, productCategory);
        assertEquals("product inventory successfully reduced", result);
    }

    @Test
    @DisplayName("The test must return a message of product not found")
    void shouldReturnProductReservationFailure(){
        given(repository.findByProductNameAndProductCategory(productName, productCategory))
                .willReturn(Optional.empty());

        String result = service.reduceProductInventory(productStock);

        verify(repository, times(1)).findByProductNameAndProductCategory(productName, productCategory);
        assertEquals("product not found", result);
    }
}
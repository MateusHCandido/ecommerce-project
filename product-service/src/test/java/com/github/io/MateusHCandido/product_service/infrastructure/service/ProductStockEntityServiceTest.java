package com.github.io.MateusHCandido.product_service.infrastructure.service;

import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductNotFoundException;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.ProductStock;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import com.github.io.MateusHCandido.product_service.infrastructure.persistence.entity.ProductEntity;
import com.github.io.MateusHCandido.product_service.infrastructure.persistence.entity.ProductStockEntity;
import com.github.io.MateusHCandido.product_service.infrastructure.persistence.repository.ProductEntityRepository;
import com.github.io.MateusHCandido.product_service.infrastructure.persistence.repository.ProductStockEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductStockEntityServiceTest {

    @Mock
    private ProductEntityRepository productEntityRepository;
    @Mock
    private ProductStockEntityRepository stockEntityRepository;
    @InjectMocks
    private ProductStockEntityService productStockEntityService;
    private ProductStockEntity productStockEntity;
    private ProductEntity productEntity;

    private ProductStock productStockDomain;

    private Product productDomain;

    @BeforeEach
    void setup(){
        productEntity = new ProductEntity("ABCD123", "Teste 01", ProductCategory.ELECTRONIC, new BigDecimal(123), "Testing");
        productStockEntity = new ProductStockEntity("ABCD4321", productEntity, 123L, LocalDateTime.now());

        productDomain = productEntity.toProduct();
        productStockDomain = new ProductStock(productEntity.toProduct(), 123L);
    }

    @Test
    @DisplayName("Should stock a product")
    void product_stock_service_stock_product_case_01(){
        String productName = productEntity.getProductName();
        ProductCategory productCategory = productEntity.getProductCategory();

        given(productEntityRepository.findByProductNameAndProductCategory(productName, productCategory))
                .willReturn(productEntity);
        given(stockEntityRepository.findByProduct(any(ProductEntity.class))).willReturn(productStockEntity);

        productStockEntityService.stockProduct(productStockDomain);

        verify(productEntityRepository, times(1)).findByProductNameAndProductCategory(productName, productCategory);
        verify(stockEntityRepository, times(1)).save(any(ProductStockEntity.class));
    }

    @Test
    @DisplayName("Should ProductNotFoundException because product not exist")
    void product_stock_service_stock_product_case_02(){
        String productName = productEntity.getProductName();
        ProductCategory productCategory = productEntity.getProductCategory();

        String expectedException = "product not found";

        given(productEntityRepository.findByProductNameAndProductCategory(productName, productCategory))
                .willReturn(null);

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productStockEntityService.stockProduct(productStockDomain);
        });


        assertEquals(expectedException, exception.getMessage());
    }


}
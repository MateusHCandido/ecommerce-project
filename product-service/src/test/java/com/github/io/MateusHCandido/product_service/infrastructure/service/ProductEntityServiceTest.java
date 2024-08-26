package com.github.io.MateusHCandido.product_service.infrastructure.service;

import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductAlreadyRegisteredException;
import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductNotFoundException;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import com.github.io.MateusHCandido.product_service.infrastructure.persistence.entity.ProductEntity;
import com.github.io.MateusHCandido.product_service.infrastructure.persistence.repository.ProductEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductEntityServiceTest {

    @Mock
    private ProductEntityRepository repository;

    @InjectMocks
    private ProductEntityService service;

    private Product productDomain;

    private ProductEntity productEntity;

    private String existingProductName;
    private ProductCategory existingProductCategory;

    @BeforeEach
    public void setup(){
        productDomain = new Product("Test 01", ProductCategory.AUTOMOTIVE, new BigDecimal(100000), "Car");
        productEntity = new ProductEntity(productDomain);


        existingProductName = productDomain.getProductName();
        existingProductCategory = productDomain.getProductCategory();
    }

    @Test
    @DisplayName("Should create a product with success and return a product domain")
    void product_service_save_case_01(){
        Product result = service.saveProduct(productDomain);

        assertNotNull(result);
        verify(repository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    @DisplayName("Should throw ProductAlreadyExistException because product already exist")
    void product_service_save_case_02(){
        String expectExceptionMessage = "product already exist";

        given(repository.existsByProductNameAndProductCategory(existingProductName, existingProductCategory))
                .willReturn(true);

        ProductAlreadyRegisteredException exception = assertThrows(ProductAlreadyRegisteredException.class
                , () -> service.saveProduct(productDomain));

        assertEquals(expectExceptionMessage, exception.getMessage());
        verify(repository, times(1))
                .existsByProductNameAndProductCategory(existingProductName, existingProductCategory);
    }


    @Test
    @DisplayName("Should update the price of product")
    void product_service_update_product_price_case_01(){
        String productName = "Test 01";
        ProductCategory productCategory = ProductCategory.AUTOMOTIVE;
        BigDecimal newProductPrice = new BigDecimal(85000);

        given(repository.findByProductNameAndProductCategory(productName, productCategory))
                .willReturn(productEntity);

        Product result = service.updateProductPrice(productName, productCategory, newProductPrice);

        assertNotNull(result);
        assertEquals(newProductPrice, result.getProductPrice());
        verify(repository, times(1)).findByProductNameAndProductCategory(productName, productCategory);
    }

    @Test
    @DisplayName("Should throw ProductNotFoundException because product not registered")
    void product_service_update_product_price_case_02(){
        String productName = "Test 01";
        ProductCategory productCategory = ProductCategory.AUTOMOTIVE;
        BigDecimal newProductPrice = new BigDecimal(85000);

        String exceptedMessage = "product not found";

        given(repository.findByProductNameAndProductCategory(productName, productCategory))
                .willReturn(null);

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            service.updateProductPrice(productName, productCategory, newProductPrice);
        });

        assertEquals(exceptedMessage, exception.getMessage());
    }
}
package com.github.io.MateusHCandido.product_service.application.usecase.product;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductAlreadyRegisteredException;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SaveProductUseCaseTest {

    @Mock
    private ProductDomainRepository repository;

    @InjectMocks
    private SaveProductUseCase useCase;

    private Product validProduct;

    private String productName;
    private ProductCategory productCategory;
    private BigDecimal productPrice;
    private String productDescription;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        productName = "Product Test 01";
        productCategory = ProductCategory.ELECTRONIC;
        productPrice = new BigDecimal(200);
        productDescription = "This is a first product for test";

        validProduct = new Product(productName, productCategory, productPrice, productDescription);
    }

    @Test
    @DisplayName("Should save and return a product")
    void product_use_case_save_product_case_01(){
        when(repository.existsByProductNameAndProductCategory(productName, productCategory)).thenReturn(false);
        when(repository.saveProduct(any(Product.class))).thenReturn(validProduct);

        Product productSaved = useCase.saveProduct(productName, productCategory.name(), productPrice.doubleValue(), productDescription);

        assertNotNull(productSaved);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException because the productCategory informed not exist")
    void product_use_case_save_product_case_02(){
        String exceptionMessage = "informed product category not exist";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            useCase.saveProduct(productName, "NOT EXIST", productPrice.doubleValue(), productDescription);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException because the productCategory is null")
    void product_use_case_save_product_case_03(){
        String exceptionMessage = "product category cannot be null";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            useCase.saveProduct(productName, null, productPrice.doubleValue(), productDescription);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Should throw ProductAlreadyRegisteredException because product already registered")
    void product_use_case_save_product_case_04(){
        String exceptionMessage = "product with name and category entered are already registered";

        when(repository.existsByProductNameAndProductCategory(productName, productCategory)).thenReturn(true);

        ProductAlreadyRegisteredException exception = assertThrows(ProductAlreadyRegisteredException.class, () -> {
            useCase.saveProduct(productName, productCategory.name(), productPrice.doubleValue(), productDescription);
        });


        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }


}
package com.github.io.MateusHCandido.product_service.application.usecase.product;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.product.UpdateProductPriceUseCase;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import com.github.io.MateusHCandido.product_service.domain.exception.BlankFieldException;
import com.github.io.MateusHCandido.product_service.domain.exception.ProductPriceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdateProductPriceUseCaseTest {

    @Mock
    private ProductDomainRepository repository;

    @InjectMocks
    private UpdateProductPriceUseCase useCase;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a product with updated price")
    void product_use_case_update_product_price_case_01(){
        ProductDomainRepository repository = mock(ProductDomainRepository.class);
        UpdateProductPriceUseCase useCase = new UpdateProductPriceUseCase(repository);

        String productName = "Product Test 01";
        ProductCategory productCategory = ProductCategory.ELECTRONIC;
        BigDecimal productPrice = new BigDecimal(200);
        String productDescription = "This is a first product for test";

        Product productUpdated = new Product(productName, productCategory, new BigDecimal(400), productDescription);
        List<Product> productList = new ArrayList<>();

        productList.add(new Product(productName, ProductCategory.ELECTRONIC, new BigDecimal(200), "This is the first product for test"));
        productList.add(new Product(productName, ProductCategory.BOOKS, new BigDecimal(50), "This is the second product for test"));
        productList.add(new Product(productName, ProductCategory.CLOTHING, new BigDecimal(100), "This is the third product for test"));
        productList.add(new Product(productName, ProductCategory.AUTOMOTIVE, new BigDecimal(300), "This is the fourth product for test"));
        productList.add(new Product(productName, ProductCategory.GAMES, new BigDecimal(25), "This is the fifth product for test"));


        when(repository.findByProductName(productName)).thenReturn(productList);
        when(repository.updateProductPrice(productName, productCategory, productPrice)).thenReturn(productUpdated);

        Product result = useCase.updateProductPrice(productName, productCategory.name(), productPrice);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Should throw NullPointerException because productName is null")
    void product_use_case_update_product_price_case_02(){
        String productName = null;
        ProductCategory productCategory = ProductCategory.ELECTRONIC;
        BigDecimal productPrice = new BigDecimal(200);
        String exceptionMessage = "Cannot invoke \"String.equals(Object)\" because \"productName\" is null";

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            useCase.updateProductPrice(productName, productCategory.name(), productPrice);
        });

        assertNull(productName);
        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Should throw BlankFieldException because productName is blank")
    void product_use_case_update_product_price_case_03(){}{
        useCase = new UpdateProductPriceUseCase(repository);

        String productName = "";
        ProductCategory productCategory = ProductCategory.ELECTRONIC;
        BigDecimal productPrice = new BigDecimal(200);
        String exceptionMessage = "product name cannot be blank";

        BlankFieldException exception = assertThrows(BlankFieldException.class, () -> {
            useCase.updateProductPrice(productName, productCategory.name(), productPrice);
        });

        assertTrue(productName.equals(""));
        assertTrue(exceptionMessage.equals(exception.getMessage()));

    }

    @Test
    @DisplayName("Should throw IllegalArgumentException because productCategory is null")
    void product_use_case_update_product_price_case_04(){}{
        String productName = "Product Test 01";
        BigDecimal productPrice = new BigDecimal(200);
        String exceptionMessage = "product category cannot be null";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            useCase.updateProductPrice(productName, null, productPrice);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Should throw ProductPriceException because productPrice is negative")
    void product_use_case_update_product_price_case_05(){}{
        String productName = "Product Test 01";
        ProductCategory productCategory = ProductCategory.ELECTRONIC;
        BigDecimal productPrice = new BigDecimal(-200);
        String exceptionMessage = "the product price cannot be negative";

        ProductPriceException exception = assertThrows(ProductPriceException.class, () -> {
            useCase.updateProductPrice(productName, productCategory.name(), productPrice);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }

}
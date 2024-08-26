package com.github.io.MateusHCandido.product_service.application.usecase.productStock;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.gateway.ProductStockDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductNotFoundException;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.ProductStock;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import com.github.io.MateusHCandido.product_service.domain.exception.BlankFieldException;
import com.github.io.MateusHCandido.product_service.domain.exception.ProductPriceException;
import com.github.io.MateusHCandido.product_service.message.KafkaProducerNewProductStock;
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
import static org.mockito.Mockito.*;

class StockProductUseCaseTest {

    @Mock
    private ProductStockDomainRepository stockDomainRepository;

    @Mock
    private ProductDomainRepository productDomainRepository;

    @Mock
    private KafkaProducerNewProductStock kafkaProducerNewProductStock;


    @InjectMocks
    private StockProductUseCase useCase;

    private String productName;
    private String productCategory;
    private Long productQuantity;
    private String productDescription;
    private ProductStock productStock;
    private Product product;

    private List<Product> productList = new ArrayList<>();

    private List<Product> emptyProductList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        productName = "Product Test 01";
        productCategory = "ELECTRONIC";
        productQuantity = 1000L;
        productDescription = "Product Test Description";

        product = new Product(productName, ProductCategory.ELECTRONIC, new BigDecimal(200), productDescription);

        productStock = new ProductStock(product, productQuantity);

        productList.add(product);
        productList.add(new Product(productName, ProductCategory.BOOKS, new BigDecimal(50), "This is the second product for test"));
        productList.add(new Product(productName, ProductCategory.CLOTHING, new BigDecimal(100), "This is the third product for test"));
        productList.add(new Product(productName, ProductCategory.AUTOMOTIVE, new BigDecimal(300), "This is the fourth product for test"));
        productList.add(new Product(productName, ProductCategory.GAMES, new BigDecimal(25), "This is the fifth product for test"));

    }

    @Test
    @DisplayName("should return a success message of stocked product")
    void product_stock_use_case_stock_product_case_01(){

        String successMessage = "Completed product inventory";

        when(productDomainRepository.findByProductName(productName)).thenReturn(productList);

        String result = useCase.stockProduct(productName, productCategory, productQuantity);

        assertNotNull(result, "The result should not be null");
        assertEquals(successMessage, result, "The success message should match the expected message");
    }

    @Test
    @DisplayName("should throw ProductNotFoundException because productName is blank")
    void product_stock_use_case_stock_product_case_02(){
        productName = "";
        String exceptionMessage = "product not found";

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            useCase.stockProduct(productName, productCategory, productQuantity);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }


    @Test
    @DisplayName("should throw ProductNotFoundException because productName is null")
    void product_stock_use_case_stock_product_case_03(){
        String exceptionMessage = "product not found";

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            useCase.stockProduct(productName, productCategory, productQuantity);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }


    @Test
    @DisplayName("should throw ProductNotFoundException because product not found")
    void product_stock_use_case_stock_product_case_04(){
        String exceptionMessage = "product not found";

        when(productDomainRepository.existsByProductNameAndProductCategory("none", ProductCategory.ELECTRONIC))
                .thenReturn(false);

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            useCase.stockProduct("none", ProductCategory.ELECTRONIC.name(), 123L);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("should throw ProductNotFoundException because productCategory is null")
    void product_stock_use_case_stock_product_case_05(){
        String exceptionMessage = "product not found";

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            useCase.stockProduct(productName, productCategory, productQuantity);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("should throw ProductNotFoundException because productCategory not exist")
    void product_stock_use_case_stock_product_case_06(){
        String exceptionMessage = "product not found";

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            useCase.stockProduct(productName, productCategory, productQuantity);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("should throw ProductPriceException because productQuantity is less or equal zero")
    void product_stock_use_case_stock_product_case_07(){
        String exceptionMessage = "the product quantity cannot be negative or equal zero";

        ProductPriceException quantityIsZero = assertThrows(ProductPriceException.class, () -> {
            useCase.stockProduct(productName, "ELETRONIC", 0L);
        });

        ProductPriceException quantityIsLessThenZero = assertThrows(ProductPriceException.class, () -> {
            useCase.stockProduct(productName, "ELETRONIC", -1L);
        });

        assertTrue(exceptionMessage.equals(quantityIsZero.getMessage()));
        assertTrue(exceptionMessage.equals(quantityIsLessThenZero.getMessage()));
    }

    @Test
    @DisplayName("should throw IllegalArgumentException because productQuantity is null")
    void product_stock_use_case_stock_product_case_08(){
        String exceptionMessage = "the product quantity cannot be null";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            useCase.stockProduct(productName, "ELETRONIC", null);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }

}
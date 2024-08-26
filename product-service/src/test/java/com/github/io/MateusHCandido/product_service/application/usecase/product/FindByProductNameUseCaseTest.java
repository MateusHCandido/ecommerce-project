package com.github.io.MateusHCandido.product_service.application.usecase.product;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.product.FindByProductNameUseCase;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import com.github.io.MateusHCandido.product_service.domain.exception.BlankFieldException;
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
import static org.mockito.Mockito.when;

class FindByProductNameUseCaseTest {
    @Mock
    private ProductDomainRepository repository;

    @InjectMocks
    private FindByProductNameUseCase useCase;

    private List<Product> productList = new ArrayList<>();

    private List<Product> emptyProductList = new ArrayList<>();

    private String productName;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        productName = "Product Test 01";

        productList.add(new Product(productName, ProductCategory.ELECTRONIC, new BigDecimal(200), "This is the first product for test"));
        productList.add(new Product(productName, ProductCategory.BOOKS, new BigDecimal(50), "This is the second product for test"));
        productList.add(new Product(productName, ProductCategory.CLOTHING, new BigDecimal(100), "This is the third product for test"));
        productList.add(new Product(productName, ProductCategory.AUTOMOTIVE, new BigDecimal(300), "This is the fourth product for test"));
        productList.add(new Product(productName, ProductCategory.GAMES, new BigDecimal(25), "This is the fifth product for test"));
    }

    @Test
    @DisplayName("Should return a list with 5 products")
    void product_use_case_find_by_product_name_case_01(){
        when(repository.findByProductName(productName)).thenReturn(productList);

        List<Product> resultList = useCase.findByProductName(productName);

        assertTrue(!resultList.isEmpty());
        assertEquals(5, resultList.size());
    }

    @Test
    @DisplayName("Should return a empty list")
    void product_use_case_find_by_product_name_case_02(){
        when(repository.findByProductName("empty")).thenReturn(emptyProductList);

        List<Product> resultList = useCase.findByProductName("empty");

        assertTrue(resultList.isEmpty());
        assertEquals(0, resultList.size());

    }
    @Test
    @DisplayName("Should throw BlankFieldException because the productName informed is blank")
    void product_use_case_find_by_product_name_case_03(){
        String exceptionMessage = "product name cannot be blank";

        BlankFieldException exception = assertThrows(BlankFieldException.class, () -> {
            useCase.findByProductName("");
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Should throw NullPointerException because the productName informed is null")
    void product_use_case_find_by_product_name_case_04(){
        String exceptionMessage = "Cannot invoke \"String.equals(Object)\" because \"productName\" is null";

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            useCase.findByProductName(null);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }
}
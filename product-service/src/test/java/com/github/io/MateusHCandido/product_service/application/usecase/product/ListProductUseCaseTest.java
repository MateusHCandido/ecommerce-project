package com.github.io.MateusHCandido.product_service.application.usecase.product;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.product.ListProductUseCase;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
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

class ListProductUseCaseTest {

    @Mock
    private ProductDomainRepository repository;

    @InjectMocks
    private ListProductUseCase useCase;

    List<Product> productList;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        productList = new ArrayList<>();

        productList.add(new Product("Product Test 01", ProductCategory.ELECTRONIC, new BigDecimal(200), "This is the first product for test"));
        productList.add(new Product("Product Test 02", ProductCategory.BOOKS, new BigDecimal(50), "This is the second product for test"));
        productList.add(new Product("Product Test 03", ProductCategory.CLOTHING, new BigDecimal(100), "This is the third product for test"));
        productList.add(new Product("Product Test 04", ProductCategory.AUTOMOTIVE, new BigDecimal(300), "This is the fourth product for test"));
        productList.add(new Product("Product Test 05", ProductCategory.GAMES, new BigDecimal(25), "This is the fifth product for test"));
    }


    @Test
    @DisplayName("Should return a list with 5 products registered")
    void product_use_case_list_products_case_01(){
        when(repository.listProduct()).thenReturn(productList);

        List<Product> resultList = useCase.listAll();

        assertTrue(!resultList.isEmpty());
        assertEquals(5, resultList.size());
    }
}
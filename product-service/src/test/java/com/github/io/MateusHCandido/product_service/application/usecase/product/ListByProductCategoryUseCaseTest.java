package com.github.io.MateusHCandido.product_service.application.usecase.product;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.product.ListByProductCategoryUseCase;
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

class ListByProductCategoryUseCaseTest {

    @Mock
    private ProductDomainRepository repository;

    @InjectMocks
    private ListByProductCategoryUseCase useCase;

    List<Product> productList = new ArrayList<>();

    List<Product> emptyProductList = new ArrayList<>();

    ProductCategory productCategory;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        emptyProductList = new ArrayList<>();
        productCategory = ProductCategory.BOOKS;

        productList.add(new Product("Product Test 01", productCategory, new BigDecimal(200), "This is the first product for test"));
        productList.add(new Product("Product Test 02", productCategory, new BigDecimal(50), "This is the second product for test"));
        productList.add(new Product("Product Test 03", productCategory, new BigDecimal(100), "This is the third product for test"));
        productList.add(new Product("Product Test 04", productCategory, new BigDecimal(300), "This is the fourth product for test"));
        productList.add(new Product("Product Test 05", productCategory, new BigDecimal(25), "This is the fifth product for test"));
    }


    @Test
    @DisplayName("Should return a list with 5 products")
    void product_use_case_list_by_category_case_01(){
        when(repository.listByProductCategory(productCategory)).thenReturn(productList);

        List<Product> resultList = useCase.listByProductCategory(productCategory.name());

        assertTrue(!resultList.isEmpty());
        assertEquals(5, resultList.size());
    }
    @Test
    @DisplayName("Should return a empty list")
    void product_use_case_list_by_category_case_02(){
        when(repository.listByProductCategory(ProductCategory.ELECTRONIC)).thenReturn(emptyProductList);

        List<Product> resultList = useCase.listByProductCategory(ProductCategory.ELECTRONIC.name());

        assertTrue(resultList.isEmpty());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException because the productCategory informed not exist")
    void product_use_case_list_by_category_case_03(){
        String exceptionMessage = "informed product category not exist";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            useCase.listByProductCategory("NOT EXIST");
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }
    @Test
    @DisplayName("Should throw IllegalArgumentException because the productCategory is null")
    void product_use_case_list_by_category_case_04(){
        String exceptionMessage = "product category cannot be null";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            useCase.listByProductCategory(null);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }
}
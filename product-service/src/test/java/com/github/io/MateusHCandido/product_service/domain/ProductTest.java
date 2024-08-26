package com.github.io.MateusHCandido.product_service.domain;

import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import com.github.io.MateusHCandido.product_service.domain.exception.BlankFieldException;
import com.github.io.MateusHCandido.product_service.domain.exception.ProductPriceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    @DisplayName("Should create a product object")
    void product_domain_case_01_create(){
        String productName = "Product Test 01";
        ProductCategory productCategory = ProductCategory.ELECTRONIC;
        BigDecimal productPrice = new BigDecimal(200);
        String productDescription = "This is a first product for test";

        verifyProductName(productName);
        verifyProductCategory(productCategory);
        verifyProductPrice(productPrice);
        verifyProductDescription(productDescription);

        Product product = new Product(productName, productCategory, productPrice, productDescription);

        assertNotNull(product);
        assertTrue(productName.equals(product.getProductName()));
        assertTrue(productCategory.equals(product.getProductCategory()));
        assertTrue(productPrice.equals(product.getProductPrice()));
        assertTrue(productDescription.equals(product.getProductDescription()));
    }

    @Test
    @DisplayName("Should throw NullPointerException because productName is null")
    void product_domain_case_02_create(){
        String productName = null;
        String exceptionMessage = "Cannot invoke \"String.equals(Object)\" because \"productName\" is null";

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            verifyProductName(productName);
        });

        assertNull(productName);
        assertTrue(exceptionMessage.equals(exception.getMessage()));

    }

    @Test
    @DisplayName("Should throw BlankFieldException because productName is blank")
    void product_domain_case_03_create(){
        String productName = "";
        String exceptionMessage = "product name cannot be blank";

        BlankFieldException exception = assertThrows(BlankFieldException.class, () -> {
            verifyProductName(productName);
        });

        assertTrue(productName.equals(""));
        assertTrue(exceptionMessage.equals(exception.getMessage()));

    }

    @Test
    @DisplayName("Should throw NullPointerException because productCategory is null")
    void product_domain_case_04_create(){
        ProductCategory productCategory = null;
        String exceptionMessage = "Cannot invoke \"com.github.io.MateusHCandido.product_service.domain.enums."
                               + "ProductCategory.equals(Object)\" because \"productCategory\" is null";

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            verifyProductCategory(productCategory);
        });

        assertNull(productCategory);
        assertTrue(exceptionMessage.equals(exception.getMessage()));

    }

    @Test
    @DisplayName("Should throw ProductPriceException because productPrice is negative")
    void product_domain_case_05_create(){
        BigDecimal productPrice = new BigDecimal(-1);
        String exceptionMessage = "the product price cannot be negative";

        ProductPriceException exception = assertThrows(ProductPriceException.class, () -> {
            verifyProductPrice(productPrice);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Should throw NullPointerException because productDescription is null")
    void product_domain_case_06_create(){
        String productDescription = null;
        String exceptionMessage = "Cannot invoke \"String.equals(Object)\" because \"productDescription\" is null";

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            verifyProductDescription(productDescription);
        });

        assertNull(productDescription);
        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }

    @Test
    @DisplayName("Should throw NullPointerException because productDescription is blank")
    void product_domain_case_07_create(){
        String productDescription = "";
        String exceptionMessage = "product description cannot be blank";

        BlankFieldException exception = assertThrows(BlankFieldException.class, () -> {
            verifyProductDescription(productDescription);
        });

        assertTrue(exceptionMessage.equals(exception.getMessage()));
    }

    protected static void verifyProductName(String productName){
        if(productName.equals(null)) throw new NullPointerException("product name cannot be null");
        productName = productName.trim();
        if(productName.isBlank()) throw new BlankFieldException("product name cannot be blank");
    }

    protected static void verifyProductCategory(ProductCategory productCategory){
        if(productCategory.equals(null)) throw new NullPointerException("product category cannot be null");
    }

    protected static void verifyProductPrice(BigDecimal productPrice){
        boolean isNegative = productPrice.signum() == -1;
        if (isNegative) throw new ProductPriceException("the product price cannot be negative");
    }

    protected static void verifyProductDescription(String productDescription){
        if(productDescription.equals(null)) throw new NullPointerException("product description cannot be null");
        productDescription = productDescription.trim();
        if(productDescription.isBlank()) throw new BlankFieldException("product description cannot be blank");
    }

}
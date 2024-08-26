package com.github.io.MateusHCandido.product_service.domain;

import com.github.io.MateusHCandido.product_service.domain.enums.ProductCategory;
import com.github.io.MateusHCandido.product_service.domain.exception.BlankFieldException;
import com.github.io.MateusHCandido.product_service.domain.exception.ProductPriceException;

import java.math.BigDecimal;

public class Product {

    private String productName;
    private ProductCategory productCategory;
    private BigDecimal productPrice;
    private String productDescription;


    public Product(){}

    public Product(String productName, ProductCategory productCategory, BigDecimal productPrice, String productDescription) {
        verifyProductName(productName);
        verifyProductCategory(productCategory);
        //verifyProductPrice(productPrice);
        verifyProductDescription(productDescription);

        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
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
        if (productPrice == null) throw new ProductPriceException("the product price cannot be null");

        Double price = productPrice.doubleValue();
        boolean isNegative = price < 0d;
        boolean isEqualZero = price == 0d;

        if (isEqualZero) throw new ProductPriceException("the product price cannot be zero");
        if (isNegative) throw new ProductPriceException("the product price cannot be negative");
    }

    protected static void verifyProductDescription(String productDescription){
        if(productDescription.equals(null)) throw new NullPointerException("product description cannot be null");
        productDescription = productDescription.trim();
        if(productDescription.isBlank()) throw new BlankFieldException("product description cannot be blank");
    }

    public String getProductName() {
        return productName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

}

package com.github.io.MateusHCandido.product_service.application.usecase.productStock;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.gateway.ProductStockDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductNotFoundException;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.ProductStock;
import com.github.io.MateusHCandido.product_service.domain.exception.ProductPriceException;
import com.github.io.MateusHCandido.product_service.message.KafkaProducerNewProductStock;

import java.util.Optional;


public class StockProductUseCase {

    private final ProductStockDomainRepository stockDomainRepository;

    private final ProductDomainRepository productDomainRepository;

    private final KafkaProducerNewProductStock producerNewProductStock;


    public StockProductUseCase(ProductStockDomainRepository repository, ProductDomainRepository productDomainRepository, KafkaProducerNewProductStock kafkaProducerNewProductStock) {
        this.stockDomainRepository = repository;
        this.productDomainRepository = productDomainRepository;
        this.producerNewProductStock = kafkaProducerNewProductStock;
    }

    public String stockProduct(String productName, String productCategory, Long productQuantity){
        verifyProductQuantity(productQuantity);

        Product product = findProductByNameAndCategory(productName, productCategory);
        ProductStock productStock = new ProductStock(product, productQuantity);

        stockDomainRepository.stockProduct(productStock);
        producerNewProductStock.sendMessage(productStock);

        return "Completed product inventory";
    }

    protected static void verifyProductQuantity(Long productQuantity){
        if (productQuantity == null) throw new IllegalArgumentException("the product quantity cannot be null");
        boolean isLessOrEqualsThanZero = productQuantity <= 0;
        if(isLessOrEqualsThanZero) throw new ProductPriceException("the product quantity cannot be negative or equal zero");
    }

    protected Product findProductByNameAndCategory(String productName, String productCategory){
        Optional<Product> product = productDomainRepository
                .findByProductName(productName)
                .stream()
                .filter(filteredProduct -> filteredProduct.getProductCategory().name().equals(productCategory))
                .findFirst();

        if (!product.isPresent()) throw new ProductNotFoundException("product not found");

        return product.get();
    }
}

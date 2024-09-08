package com.github.io.MateusHCandido.product_service.application.usecase.productStock;

import com.github.io.MateusHCandido.product_service.application.gateway.ProductDomainRepository;
import com.github.io.MateusHCandido.product_service.application.gateway.ProductStockDomainRepository;
import com.github.io.MateusHCandido.product_service.application.usecase.exception.ProductNotFoundException;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.domain.ProductStock;
import com.github.io.MateusHCandido.product_service.domain.exception.ProductPriceException;
import com.github.io.MateusHCandido.product_service.message.KafkaProducerReduceProductStock;

import java.util.Optional;

public class ReduceStockProductUseCase {
    private final ProductStockDomainRepository stockDomainRepository;

    private final ProductDomainRepository productDomainRepository;

    private final KafkaProducerReduceProductStock kafkaProducerReduceProductStock;

    public ReduceStockProductUseCase(ProductStockDomainRepository stockDomainRepository, ProductDomainRepository productDomainRepository, KafkaProducerReduceProductStock kafkaProducerReduceProductStock) {
        this.stockDomainRepository = stockDomainRepository;
        this.productDomainRepository = productDomainRepository;
        this.kafkaProducerReduceProductStock = kafkaProducerReduceProductStock;
    }

    public String reduceStockProduct(String productName, String productCategory, Long quantityForReduce){
        verifyProductQuantity(quantityForReduce);

        Product product = findProductByNameAndCategory(productName, productCategory);
        ProductStock productStock = new ProductStock(product, quantityForReduce);

        stockDomainRepository.reduceStock(productStock);
        kafkaProducerReduceProductStock.sendMessage(productStock);

        return "product inventory successfully reduced";
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

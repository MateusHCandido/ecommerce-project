package com.github.io.MateusHCandido.product_service.infrastructure.controller;

import com.github.io.MateusHCandido.product_service.application.usecase.product.*;
import com.github.io.MateusHCandido.product_service.domain.Product;
import com.github.io.MateusHCandido.product_service.infrastructure.controller.dto.ProductPutDto;
import com.github.io.MateusHCandido.product_service.infrastructure.controller.dto.ProductRequestDto;
import com.github.io.MateusHCandido.product_service.infrastructure.controller.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private FindByProductNameUseCase findByProductNameUseCase;

    @Autowired
    private ListByProductCategoryUseCase listByProductCategoryUseCase;

    @Autowired
    private ListProductUseCase listProductUseCase;

    @Autowired
    private SaveProductUseCase saveProductUseCase;

    @Autowired
    private UpdateProductPriceUseCase updateProductPriceUseCase;

    @PostMapping("/save/product")
    public ProductResponseDto saveProduct(@RequestBody @Valid ProductRequestDto dto){
        Product product = saveProductUseCase
                .saveProduct(dto.getProductName(),
                                dto.getProductCategory(),
                                dto.getProductPrice().doubleValue(),
                                dto.getProductDescription()
                            );
        return new ProductResponseDto(product);
    }

    @PutMapping("/update-price")
    public ProductResponseDto updateProductPrice(@RequestBody @Valid ProductPutDto dto){
        Product product = updateProductPriceUseCase
                .updateProductPrice(dto.getProductName(),
                        dto.getProductCategory(),
                        new BigDecimal(dto.getProductPrice()));

        return new ProductResponseDto(product);
    }

    @GetMapping("/list-products")
    public List<ProductResponseDto> listProduct(){
        return listProductUseCase.listAll()
                .stream()
                .map(product -> new ProductResponseDto(product))
                .collect(Collectors.toList());
    }

    @GetMapping("/list-product-by-category/{category}")
    public List<ProductResponseDto> listByProductCategory(@RequestParam String category){
        return listByProductCategoryUseCase.listByProductCategory(category)
                .stream()
                .map(product -> new ProductResponseDto(product))
                .collect(Collectors.toList());
    }

    @GetMapping("/list-product-by-name/{name}")
    public List<ProductResponseDto> listByProductName(@RequestParam String name){
        return findByProductNameUseCase.findByProductName(name)
                .stream()
                .map(product -> new ProductResponseDto(product))
                .collect(Collectors.toList());
    }
}

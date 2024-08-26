package com.github.io.MateusHCandido.product_service.infrastructure;

import com.github.io.MateusHCandido.product_service.application.usecase.productStock.StockProductUseCase;
import com.github.io.MateusHCandido.product_service.infrastructure.controller.dto.StockProductRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock")
public class ProductStockController {

    @Autowired
    private StockProductUseCase stockProductUseCase;

    @PostMapping("/stock-product")
    public String stockProduct(@RequestBody StockProductRequestDto dto){
        return stockProductUseCase.stockProduct(dto.getProductName(), dto.getProductCategory(), dto.getProductQuantity());
    }
}

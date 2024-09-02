package com.io.github.MateusHCandido.inventory_service.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String productName;
    private String productCategory;
    private BigDecimal productPrice;
    private String productDescription;

}

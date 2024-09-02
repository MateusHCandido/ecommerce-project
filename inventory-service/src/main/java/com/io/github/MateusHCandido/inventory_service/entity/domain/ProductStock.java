package com.io.github.MateusHCandido.inventory_service.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductStock {

    private Product product;
    private Long quantityForStock;
}

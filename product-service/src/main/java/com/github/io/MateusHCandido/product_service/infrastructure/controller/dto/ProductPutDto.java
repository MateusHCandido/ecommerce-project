package com.github.io.MateusHCandido.product_service.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPutDto {

    @NotBlank(message = "productName cannot be blank")
    private String productName;
    @NotBlank(message = "productCategory cannot be blank")
    private String productCategory;
    @NotNull(message = "productPrice cannot be null")
    private Double productPrice;
}

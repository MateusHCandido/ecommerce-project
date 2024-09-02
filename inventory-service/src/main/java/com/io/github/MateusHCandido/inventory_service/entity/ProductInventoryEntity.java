package com.io.github.MateusHCandido.inventory_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductInventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long inventoryId;
    @NotBlank(message = "the productName cannot be blank")
    private String productName;
    @NotBlank(message = "the productCategory cannot be blank")
    private String productCategory;
    @NotNull(message = "the productPrice cannot be null")
    private BigDecimal productPrice;
    @NotBlank(message = "the productDescription cannot be blank")
    private String productDescription;
    @NotNull(message = "the quantityForStock cannot be null")
    private Long quantityForStock;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Fortaleza")
    private LocalDateTime updatedInventoryTimestamp;

    public void increaseInventory(Long quantityForStock){
        this.quantityForStock += quantityForStock;
    }

    public void reduceInventory(Long quantityForReduce){
        this.quantityForStock -= quantityForReduce;
    }

    @PrePersist
    protected void onCreate(){
        this.updatedInventoryTimestamp = LocalDateTime.now();
    }

    public void updateDescription(String description) {
        this.productDescription = description;
    }
}

package com.mayur.FlipCommerce.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long Id;
    private Integer quantity;
    private Integer pricePerUnit;
    private Integer totalItemPrice;
    private LocalDate createdAt;
}

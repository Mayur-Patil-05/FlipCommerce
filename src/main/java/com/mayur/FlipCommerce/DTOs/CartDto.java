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
public class CartDto {
    private Long Id;
    private Integer totalQuantity;
    private Integer totalPrice;
    private LocalDate createdAt;
}

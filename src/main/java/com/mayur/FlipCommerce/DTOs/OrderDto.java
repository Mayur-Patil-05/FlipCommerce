package com.mayur.FlipCommerce.DTOs;

import com.mayur.FlipCommerce.Model.Enum.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long Id;
    private Integer totalAmount;
    private OrderStatus status;
    private LocalDate placedAt;
    private LocalDate deliveredAt;
    private LocalDate createdAt;
}

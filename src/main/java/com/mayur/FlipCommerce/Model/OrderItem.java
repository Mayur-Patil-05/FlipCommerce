package com.mayur.FlipCommerce.Model;

import com.mayur.FlipCommerce.Model.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer quantity;
    private Integer pricePerUnit;
    private Integer totalItemPrice;
    private LocalDate createdAt;

    @PrePersist
    public void created() {
        this.createdAt = LocalDate.now();
    }
}

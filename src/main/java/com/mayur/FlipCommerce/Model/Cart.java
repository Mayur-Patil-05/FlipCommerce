package com.mayur.FlipCommerce.Model;

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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer totalQuantity;
    private Integer totalPrice;
    private LocalDate createdAt;

    @PrePersist
    public void created() {
        this.createdAt = LocalDate.now();
    }
}

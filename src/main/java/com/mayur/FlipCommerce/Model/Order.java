package com.mayur.FlipCommerce.Model;

import com.mayur.FlipCommerce.Model.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDate placedAt;
    private LocalDate deliveredAt;
    private LocalDate createdAt;

    @PrePersist
    public void created() {
        this.createdAt = LocalDate.now();
    }
}

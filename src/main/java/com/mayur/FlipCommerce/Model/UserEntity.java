package com.mayur.FlipCommerce.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Boolean isVerified;
    private LocalDate createdAt;
    private String address;

    @PrePersist
    public void created() {
        this.createdAt = LocalDate.now();
    }
}

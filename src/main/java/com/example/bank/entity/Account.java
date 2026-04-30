package com.example.bank.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountNumber;

    private String accountType; // S or C

    private Double availableBalance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}

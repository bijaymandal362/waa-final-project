package com.miu.waafinalproject.SecureOnlineAuctionSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositID;
    private double depositAmount;
    @ManyToOne
    private Customer customer;
    // Other deposit-related properties and methods

    @ManyToOne
    private Product product;
}

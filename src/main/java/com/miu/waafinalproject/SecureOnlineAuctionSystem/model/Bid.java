package com.miu.waafinalproject.SecureOnlineAuctionSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bidID;
    private double bidAmount;
    private Date bidDueDate;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Product product;
    // Other bid-related properties and methods
}

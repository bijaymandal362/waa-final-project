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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "customer")
    private List<Bid> bids;
    @OneToMany(mappedBy = "customer")
    private List<Deposit> deposits;
    // Other customer-specific properties and methods
}

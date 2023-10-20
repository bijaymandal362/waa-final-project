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
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sellerID;
    @OneToOne
    private Users users;
    @OneToMany(mappedBy = "seller")
    private List<Product> products;
    // Other seller-specific properties and methods
}

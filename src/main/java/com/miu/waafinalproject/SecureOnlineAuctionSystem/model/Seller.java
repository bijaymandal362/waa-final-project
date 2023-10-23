package com.miu.waafinalproject.SecureOnlineAuctionSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long sellerID;
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Users users;
    @OneToMany(mappedBy = "seller")
    @JsonIgnore
    private List<Product> products;
    // Other seller-specific properties and methods
}

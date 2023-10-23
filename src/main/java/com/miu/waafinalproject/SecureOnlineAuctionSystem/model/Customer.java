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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerID;
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Users users;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Bid> bids;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Deposit> deposits;
    // Other customer-specific properties and methods
}

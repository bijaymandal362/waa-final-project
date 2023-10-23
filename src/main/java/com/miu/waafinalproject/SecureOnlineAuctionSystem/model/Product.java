package com.miu.waafinalproject.SecureOnlineAuctionSystem.model;

import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    private String name;
    private String description;
    private double startingPrice;
    private double deposit;
    private Date bidDueDate;
    private Date biddingPaymentDueDate;
    private boolean released;
    private boolean sold;
    @ManyToOne
    private Seller seller;
    @OneToMany(mappedBy = "product")
    private List<Bid> bids;
    // Other product-related properties and methods

    @OneToMany(mappedBy = "product")
    private List<Deposit> deposits;
}

package com.miu.waafinalproject.SecureOnlineAuctionSystem.model;

import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;
    private String name;
    private String description;
    private double startingPrice;
    private double deposit;
    private Date bidDueDate;
    private Date biddingPaymentDueDate;
    private boolean released;
    @ManyToOne
    private Seller seller;
    @OneToMany(mappedBy = "product")
    private List<Bid> bids;
    // Other product-related properties and methods
}
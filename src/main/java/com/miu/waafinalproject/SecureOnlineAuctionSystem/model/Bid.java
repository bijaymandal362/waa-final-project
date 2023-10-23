package com.miu.waafinalproject.SecureOnlineAuctionSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long bidID;
    private double bidAmount;
    private Date bidDate;
    @ManyToOne
    @JsonIgnore
    private Customer customer;
    @ManyToOne
    private Product product;
    // Other bid-related properties and methods
}

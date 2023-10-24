package com.miu.waafinalproject.SecureOnlineAuctionSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Winner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    private Product product; // Reference to the product that was won

    @ManyToOne
    @JsonIgnore
    private Customer customer; // Reference to the customer who placed the winning bid

    private Double winningBidAmount; // The amount of the winning bid

    // Other properties and methods as needed
}


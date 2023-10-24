package com.miu.waafinalproject.SecureOnlineAuctionSystem.dto;

import lombok.Data;

@Data
public class WinnerDto {
    private Long productId;
    private String productName;
    private Long customerId;
    private String username;
    private double highestBid;

    // Constructors, getters, and setters
}

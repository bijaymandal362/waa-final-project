package com.miu.waafinalproject.SecureOnlineAuctionSystem.dto;

import lombok.Data;

@Data
public class BidAddUpdateDto {
    private Long customerId;
    private Long productId;
    private double newBidAmount;
}

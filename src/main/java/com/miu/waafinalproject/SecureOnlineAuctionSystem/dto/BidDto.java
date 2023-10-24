package com.miu.waafinalproject.SecureOnlineAuctionSystem.dto;

import lombok.Data;

@Data
public class BidDto {
    private Long bidId;
    private Double bidAmount;
    private Long customerId;
    private String username;
    private ProductDto product;
}

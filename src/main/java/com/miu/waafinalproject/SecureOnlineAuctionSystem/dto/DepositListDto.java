package com.miu.waafinalproject.SecureOnlineAuctionSystem.dto;

import lombok.Data;

@Data
public class DepositListDto {
    private Long depositId;
    private Double amount;
    private Long productId;
    private String productName;
}

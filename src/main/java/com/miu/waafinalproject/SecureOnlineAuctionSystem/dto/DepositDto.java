package com.miu.waafinalproject.SecureOnlineAuctionSystem.dto;

import lombok.Data;

@Data
public class DepositDto {
    private Long id;
    private Double amount;
  private Long customerId;
  private Long productId;
}

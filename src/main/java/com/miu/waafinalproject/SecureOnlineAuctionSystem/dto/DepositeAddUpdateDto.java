package com.miu.waafinalproject.SecureOnlineAuctionSystem.dto;

import lombok.Data;

@Data
public class DepositeAddUpdateDto {
    private Long customerId;
    private Long productId;
    private double depositAmount;
}

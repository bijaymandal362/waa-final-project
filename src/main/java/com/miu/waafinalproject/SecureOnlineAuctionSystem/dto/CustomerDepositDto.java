package com.miu.waafinalproject.SecureOnlineAuctionSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDepositDto {
    private Long customerId;
    private List<DepositListDto> deposits;
}

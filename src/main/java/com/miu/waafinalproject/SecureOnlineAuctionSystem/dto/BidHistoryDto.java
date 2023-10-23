package com.miu.waafinalproject.SecureOnlineAuctionSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class BidHistoryDto {
    private Long customerId;
    private String username;
    private List<BidProductHistoryDto> productHistory;
}

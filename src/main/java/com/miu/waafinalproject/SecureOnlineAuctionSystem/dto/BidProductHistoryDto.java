package com.miu.waafinalproject.SecureOnlineAuctionSystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BidProductHistoryDto {
    private String productName;
    private Date bidDate;
    private double amount;
}

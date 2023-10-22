package com.miu.waafinalproject.SecureOnlineAuctionSystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDto {
    private Long productID;
    private String name;
    private String description;
    private double startingPrice;
    private double deposit;
    private Date bidDueDate;
    private Date biddingPaymentDueDate;
    private boolean released;
}

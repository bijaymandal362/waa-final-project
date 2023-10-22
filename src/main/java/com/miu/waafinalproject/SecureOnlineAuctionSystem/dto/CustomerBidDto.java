package com.miu.waafinalproject.SecureOnlineAuctionSystem.dto;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Bid;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Deposit;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Product;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Users;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class CustomerBidDto {

    private int customerID;

    private Users users;

    private List<Bid> bids;

    private List<Deposit> deposits;

    private Product product;
}

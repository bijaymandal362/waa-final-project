package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Bid;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.BidAddUpdateDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.BidHistoryDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.CustomerBidResultDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.CustomerDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Bid;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Customer;

import java.util.List;

public interface IBidService {
    Bid createBid(BidAddUpdateDto bid);
    Bid getHighestBidder(Long productId);
    Double getHighestBidForCustomerAndProduct(Long customerId, Long productId);
    CustomerBidResultDto getHighestBidForProduct(Long productId);

    BidHistoryDto getBidHistoryForCustomer(Long customerId);
}

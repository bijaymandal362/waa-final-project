package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Bid;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.BidAddUpdateDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.CustomerDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Bid;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Customer;

public interface IBidService {
    Bid createBid(BidAddUpdateDto bid);
    Bid getHighestBidder(Long productId);

    void updateBid(BidAddUpdateDto bidUpdateDTO);
    CustomerDto closeBiddingForExpiredProducts();
}

package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Bid;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.BidAddUpdateDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Bid;

public interface IBidService {
    Bid createBid(BidAddUpdateDto bid);
    Bid getHighestBidder(Long productId);

    void updateBid(BidAddUpdateDto bidUpdateDTO);
}

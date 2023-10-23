package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Customer;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.BidAddUpdateDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.CustomerBidDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Customer;

public interface ICustomerService {
    boolean isEligibleToBid(BidAddUpdateDto bidAddUpdateDTO);
    Customer getCustomerById(int id);

}

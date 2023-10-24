package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Winner;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.WinnerDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Product;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Winner;

import java.util.List;

public interface IWinnerService {

    void selectWinnersForProducts();
    List<Winner> getAllWinners();
}

package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Deposit;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.DepositDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.DepositeAddUpdateDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Deposit;

public interface IDepositService {
    DepositDto createDeposite(DepositeAddUpdateDto deposite);
}

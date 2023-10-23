package com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions;

public class DepositBiddingLessThanStartingPriceException extends RuntimeException{
    public DepositBiddingLessThanStartingPriceException(String message) {
        super(message);
    }
}

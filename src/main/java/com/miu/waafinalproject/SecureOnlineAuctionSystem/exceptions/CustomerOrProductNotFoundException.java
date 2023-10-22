package com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions;

public class CustomerOrProductNotFoundException extends RuntimeException{
    public CustomerOrProductNotFoundException(String message) {
        super(message);
    }
}

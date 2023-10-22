package com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions;

public class ProductUpdateNotAllowedException extends RuntimeException{
    public ProductUpdateNotAllowedException(String message) {
        super(message);
    }
}

package com.miu.waafinalproject.SecureOnlineAuctionSystem.exception;

public class ProductUpdateNotAllowedException extends RuntimeException{
    public ProductUpdateNotAllowedException(String message) {
        super(message);
    }
}

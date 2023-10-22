package com.miu.waafinalproject.SecureOnlineAuctionSystem.exception;

public class ProductDeleteNotAllowedException extends RuntimeException{
    public ProductDeleteNotAllowedException(String message) {
        super(message);
    }
}

package com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions;

public class ProductDeleteNotAllowedException extends RuntimeException{
    public ProductDeleteNotAllowedException(String message) {
        super(message);
    }
}

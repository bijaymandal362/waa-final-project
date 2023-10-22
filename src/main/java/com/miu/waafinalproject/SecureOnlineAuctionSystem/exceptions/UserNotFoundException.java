package com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super("Sorry, this user could not be found");
    }
}

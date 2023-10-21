package com.miu.waafinalproject.SecureOnlineAuctionSystem.model.auth;

import lombok.Data;

@Data
public class AuthRequest {

    private String email;
    private String password;

}

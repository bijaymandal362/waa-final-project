package com.miu.waafinalproject.SecureOnlineAuctionSystem.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String email;
    private String accessToken;

}
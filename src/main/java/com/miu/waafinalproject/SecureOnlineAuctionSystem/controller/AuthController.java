package com.miu.waafinalproject.SecureOnlineAuctionSystem.controller;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.enums.RolesEnum;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Users;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.auth.AuthRequest;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.auth.AuthResponse;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.CustomerRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.SellerRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomerRepo customerRepo;
    private final SellerRepo sellerRepo;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {

        //authentiate
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    authRequest.getEmail(),
                                    authRequest.getPassword())
                    );

            Users users = (Users) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(users);
            String role = users.getRoles().toString();
            Long roleCustomerSellerId = null;

            if (RolesEnum.CUSTOMER.equals(users.getRoles())) {
                // Set the customer ID if the role is "CUSTOMER"
                roleCustomerSellerId = customerRepo.findByUser(users).getCustomerID();
            }else{
                if(RolesEnum.SELLER.equals(users.getRoles())){
                    // Set the seller ID if the role is "SELLER"
                    roleCustomerSellerId = sellerRepo.findByUser(users).getSellerID();
                }
            }
            //return token
            return ResponseEntity.ok(new AuthResponse(users.getEmail(), token,role, roleCustomerSellerId));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        //failed -> throw unauthorized
    }


}


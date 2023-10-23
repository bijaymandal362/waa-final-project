package com.miu.waafinalproject.SecureOnlineAuctionSystem.controller;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.DepositDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.DepositeAddUpdateDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Deposit;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Deposit.IDepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposits")
@RequiredArgsConstructor
public class DepositController {
    private final IDepositService depositService;


    @PostMapping()
    public ResponseEntity<DepositDto> createDeposit(@RequestBody DepositeAddUpdateDto deposit) {
        DepositDto createdDeposit = depositService.createDeposite(deposit);
        return ResponseEntity.ok(createdDeposit);
    }

    @GetMapping("/{customerid}")
    public ResponseEntity<?> getCustomerDeposits(@PathVariable Long customerid) {
        return ResponseEntity.ok(depositService.getCustomerDeposits(customerid));
    }
}


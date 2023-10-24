package com.miu.waafinalproject.SecureOnlineAuctionSystem.controller;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Winner;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Winner.WinnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/winners")
@RequiredArgsConstructor
public class WinnerController {
    private final WinnerService winnerService;
    @GetMapping
    public ResponseEntity<List<Winner>> getAllWinners() {
        List<Winner> winners = winnerService.getAllWinners();
        return ResponseEntity.ok(winners);
    }
}

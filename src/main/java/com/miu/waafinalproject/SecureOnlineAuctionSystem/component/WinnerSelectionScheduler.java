package com.miu.waafinalproject.SecureOnlineAuctionSystem.component;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Winner.WinnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WinnerSelectionScheduler {


    private final WinnerService winnerSelectionService;

    @Scheduled(cron = "0 0 0 * * ?") // Run at midnight
    public void selectWinnerTask() {
        winnerSelectionService.selectWinnersForProducts();
    }
}
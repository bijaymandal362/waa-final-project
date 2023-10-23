package com.miu.waafinalproject.SecureOnlineAuctionSystem.controller;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.BidAddUpdateDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.CustomerDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Bid;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Bid.IBidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bids")
@RequiredArgsConstructor
public class BidController {
    private final IBidService bidService;



    @PostMapping()
    public ResponseEntity<Bid> createBid(@RequestBody BidAddUpdateDto bid) {
        Bid createdBid = bidService.createBid(bid);
        return ResponseEntity.ok(createdBid);
    }

    @GetMapping("/highest-bid/{productId}")
    public ResponseEntity<Bid> getHighestBidder(@PathVariable Long productId) {
        Bid highestBid = bidService.getHighestBidder(productId);
        return ResponseEntity.ok(highestBid);
    }

    @PutMapping()
    public ResponseEntity<Void> updateBid(@RequestBody BidAddUpdateDto bidUpdateDTO) {
        bidService.updateBid(bidUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/close-bidding")
    public ResponseEntity<CustomerDto> closeBiddingForExpiredProducts() {
        CustomerDto winner = bidService.closeBiddingForExpiredProducts();
        return ResponseEntity.ok(winner);
    }
}
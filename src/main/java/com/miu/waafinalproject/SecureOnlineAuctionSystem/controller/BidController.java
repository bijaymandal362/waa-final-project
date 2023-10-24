package com.miu.waafinalproject.SecureOnlineAuctionSystem.controller;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.*;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Bid;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Bid.IBidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/by-product")
    public ResponseEntity<List<BidDto>> findBidsByProductID(@RequestParam Long productId) {
        List<BidDto> bids = bidService.findBidsByProductID(productId);

        if (bids.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(bids, HttpStatus.OK);
        }
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Bid> getHighestBidder(@PathVariable Long productId) {
        Bid highestBid = bidService.getHighestBidder(productId);
        return ResponseEntity.ok(highestBid);
    }

    @GetMapping("/latest-customer--bid/{customerId}/{productId}")
    public ResponseEntity<Double> getHighestBidForCustomerAndProduct(@PathVariable Long customerId, @PathVariable Long productId) {
        Double highestBid = bidService.getHighestBidForCustomerAndProduct(customerId, productId);
        return ResponseEntity.ok(highestBid);
    }

    @GetMapping("/highest-bid/{productId}")
    public ResponseEntity<CustomerBidResultDto> getHighestBidForProduct(@PathVariable Long productId) {
        CustomerBidResultDto highestBid = bidService.getHighestBidForProduct(productId);
        return ResponseEntity.ok(highestBid);
    }

    @GetMapping("/bid-history/{customerId}")
    public ResponseEntity<BidHistoryDto> getBidHistoryForCustomer(@PathVariable Long customerId) {
        BidHistoryDto bidHistory = bidService.getBidHistoryForCustomer(customerId);
        return ResponseEntity.ok(bidHistory);
    }
}
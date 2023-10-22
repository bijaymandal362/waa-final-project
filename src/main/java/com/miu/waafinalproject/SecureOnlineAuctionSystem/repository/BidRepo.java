package com.miu.waafinalproject.SecureOnlineAuctionSystem.repository;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepo extends JpaRepository<Bid, Integer> {
}

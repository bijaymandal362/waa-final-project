package com.miu.waafinalproject.SecureOnlineAuctionSystem.repository;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinnerRepo extends JpaRepository<Winner, Long> {
}

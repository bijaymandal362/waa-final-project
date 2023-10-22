package com.miu.waafinalproject.SecureOnlineAuctionSystem.repository;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Seller;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Long>{

    @Query("SELECT s FROM Seller s WHERE s.users = :user")
    Seller findByUser(@Param("user") Users user);
}

package com.miu.waafinalproject.SecureOnlineAuctionSystem.repository;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Bid;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Customer;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BidRepo extends JpaRepository<Bid, Long> {
    @Query("SELECT b FROM Bid b WHERE b.product.productID = :productId ORDER BY b.bidAmount DESC")
    Optional<Bid> findHighestBidByProductId(@Param("productId") Long productId);

    @Query("SELECT b FROM Bid b WHERE b.customer = :customer AND b.product = :product")
    Bid findBidByCustomerAndProduct(@Param("customer") Customer customer, @Param("product") Product product);
}

package com.miu.waafinalproject.SecureOnlineAuctionSystem.repository;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.CustomerBidResultDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Bid;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Customer;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BidRepo extends JpaRepository<Bid, Long> {
    @Query("SELECT b FROM Bid b WHERE b.product.productID = :productId ORDER BY b.bidAmount DESC")
    List<Bid> findBidsByProductIdOrderByBidAmountDesc(@Param("productId") Long productId);
    @Query("SELECT b FROM Bid b WHERE b.product.productID = :productId AND b.bidAmount = (SELECT MAX(b2.bidAmount) FROM Bid b2 WHERE b2.product.productID = :productId)")
    Bid findHighestBidRecordForProduct(@Param("productId") Long productId);
    @Query("SELECT MAX(b.bidAmount) FROM Bid b WHERE b.product.productID = :productId")
    Double findHighestBidForProducts(@Param("productId") Long productId);
    @Query("SELECT b FROM Bid b WHERE b.customer = :customer AND b.product = :product")
    Bid findBidByCustomerAndProduct(@Param("customer") Customer customer, @Param("product") Product product);

    @Query("SELECT b FROM Bid b WHERE b.product = :product ORDER BY b.bidAmount DESC")
    Bid findHighestBidForProduct(@Param("product") Product product);

    @Query("SELECT MAX(b.bidAmount) FROM Bid b WHERE b.customer.customerID = :customerId AND b.product.productID = :productId")
    Optional<Double> findHighestBidByCustomerAndProduct(@Param("customerId") Long customerId, @Param("productId") Long productId);

    @Query("SELECT b.customer.users.userName AS username, MAX(b.bidAmount) AS highestBid " +
            "FROM Bid b " +
            "WHERE b.product.productID = :productId")
    CustomerBidResultDto findHighestBidForProduct(@Param("productId") Long productId);

    @Query("SELECT b FROM Bid b WHERE b.customer.customerID = :customerId ORDER BY b.bidDate")
    List<Bid> findByCustomerCustomerIDOrderByBidDate(@Param("customerId") Long customerId);



}

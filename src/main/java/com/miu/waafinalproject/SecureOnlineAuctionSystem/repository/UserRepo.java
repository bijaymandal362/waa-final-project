package com.miu.waafinalproject.SecureOnlineAuctionSystem.repository;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long>{
}

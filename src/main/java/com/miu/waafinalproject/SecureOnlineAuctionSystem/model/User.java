package com.miu.waafinalproject.SecureOnlineAuctionSystem.model;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.enums.RolesEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    private String email;
    private String password; // Encrypted password
    private String licenseNumber;
    private RolesEnum role;
    // Other user-related properties and methods
}


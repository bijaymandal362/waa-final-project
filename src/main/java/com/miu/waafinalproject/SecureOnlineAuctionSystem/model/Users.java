package com.miu.waafinalproject.SecureOnlineAuctionSystem.model;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.enums.RolesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    private String userName;

//  email column should be unique and not null
//  @NaturalId(mutable = true)
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    //password
    @Column(nullable = false, length = 64)
    private String password; // Encrypted password

    @Column(nullable = false, length = 17)
    private String licenseNumber;


    private RolesEnum roles;
    // Other user-related properties and methods


    //Overriden from UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}



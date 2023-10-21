package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.User;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Users;

import java.util.List;

public interface IUserService {

    Users save(Users u);

    List<Users> getAllUsers();

    Users getUserById(int id);

    void deleteUserById(int id);

    Users updateUserById(int id, Users users);

}
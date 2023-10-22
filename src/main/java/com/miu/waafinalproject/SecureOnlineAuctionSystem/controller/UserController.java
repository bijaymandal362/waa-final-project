package com.miu.waafinalproject.SecureOnlineAuctionSystem.controller;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.ApiResponse;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Users;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.services.User.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    //save Users
    @PostMapping
    public ResponseEntity<Users> RegistrationUser(@RequestBody Users u){
        Users users = userService.Registration(u);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    //Get all Users
    @GetMapping
    public ResponseEntity<List<Users>> getUsers(){
        List<Users> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    //Get User By Id
    @GetMapping("/{id}")
    public Users getSpecificUser(@PathVariable int id){
        return this.userService.getUserById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable int id, @RequestBody Users users){
        Users updatedUsers = userService.updateUserById(id, users);
        return ResponseEntity.ok(updatedUsers);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }


}


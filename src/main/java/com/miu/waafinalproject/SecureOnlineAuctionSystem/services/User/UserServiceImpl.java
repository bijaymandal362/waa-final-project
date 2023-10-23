package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.User;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.enums.RolesEnum;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.UserAlreadyExistsException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.UserNotFoundException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Customer;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Seller;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Users;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.CustomerRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.SellerRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService{

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepo customerRepository;
    private final SellerRepo sellerRepository;

    @Override
    public Users Registration(Users users) {
        if(userAlreadyExists(users.getEmail())){
            throw new UserAlreadyExistsException(users.getEmail() + " already exists");
        }

        //encode the password and then only save to the repository
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        System.out.println("Role Value: " + users.getRoles());
        users.setLicenseNumber(users.getLicenseNumber());
        users.setUserName(users.getUsername());
        users.setRoles(users.getRoles());
        userRepository.save(users);
// Determine the user's role
        if (users.getRoles()==RolesEnum.CUSTOMER) {
            users.setRoles(RolesEnum.CUSTOMER);
            Customer customer = new Customer();
            customer.setUsers(users);
            customerRepository.save(customer);
        } else if (users.getRoles()==RolesEnum.SELLER) {
            users.setRoles(RolesEnum.SELLER);
            Seller seller = new Seller();
            seller.setUsers(users);
            sellerRepository.save(seller);
        }

        return users;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(int id) {
        return userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("Sorry, this user could not be found"));
    }

    @Override
    public void deleteUserById(int id) {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("Sorry, User not Found");
        }

        this.userRepository.deleteById(id);
    }

    @Override
    public Users updateUserById(int id, Users users) {
        return userRepository.findById(id).map(u ->{
            u.setEmail(users.getEmail());
            u.setPassword(users.getPassword());
            u.setLicenseNumber(users.getLicenseNumber());
            u.setRoles(users.getRoles());

            return userRepository.save(u);
        }).orElseThrow(()-> new UserNotFoundException("Sorry, this user could not be found"));
    }


    private boolean userAlreadyExists(String email){
        return userRepository.findByEmail(email).isPresent();
    }
}

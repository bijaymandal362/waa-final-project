package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Customer;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.BidAddUpdateDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.CustomerBidDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.enums.RolesEnum;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.CustomerCanOnlyBidExceptions;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.DepositeNotNullException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.*;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.CustomerRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.ProductRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements  ICustomerService
{
    private final UserRepo usersRepository;
    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;
    @Override
    public Customer getCustomerById(int id) {
        return null;
    }

    @Override
    public Customer createBid(CustomerBidDto customer) {
        if(customer.getUsers().getRoles()== RolesEnum.CUSTOMER){
            Customer customer1 = new Customer();
            customer1.setUsers(customer.getUsers());

            boolean isDepositable = addDeposit(customer.getDeposits().stream().toList());
            if(!isDepositable){
                throw new DepositeNotNullException("Deposit amount cannot be null");
            }else{
                customer1.setDeposits(customer.getDeposits());
            }
            customer1.setDeposits(customer.getDeposits());
            customer1.setBids(customer.getBids());
            customerRepo.save(customer1);
            return customer1;

        }else{
            throw new CustomerCanOnlyBidExceptions("Only customer can bid");
        }

    }



    private boolean addDeposit(List<Deposit> deposits) {
        boolean allDepositsValid = true;

        for (Deposit deposit : deposits) {
            if (deposit.getProduct() == null) {
                allDepositsValid = false;
                // Handle the case where a deposit doesn't have a product
            } else {
                double startingPrice = deposit.getProduct().getStartingPrice();
                double requiredDeposit = startingPrice * 1.10; // 10% greater than starting price

                if (deposit.getDepositAmount() >= requiredDeposit) {
                    deposits.add(deposit); // Add the deposit to the customer
                } else {
                    allDepositsValid = false;
                    // Handle the case where a deposit is insufficient
                }
            }
        }

        return allDepositsValid;
    }
//    private Users getUsers()
//    {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String username = userDetails.getUsername();
//
//        Users user = usersRepository.findByUsername(username);
//
//        return user;
//    }
//
//    public int getUserIdByUsername(String username) {
//        Users user = usersRepository.findByUsername(username);
//        if (user != null) {
//            return user.getUserID();
//        }
//        return -1; // or some other error handling strategy
//    }




    public boolean isEligibleToBid(BidAddUpdateDto bidAddUpdateDTO) {
        if (bidAddUpdateDTO == null) {
            return false;
        }

        Customer customer = customerRepo.findById(bidAddUpdateDTO.getCustomerId()).orElse(null);
        Product product = productRepo.findById(bidAddUpdateDTO.getProductId()).orElse(null);

        if (customer == null || product == null) {
            return false;
        }

        return hasRequiredDeposit(customer, product, bidAddUpdateDTO.getNewBidAmount());
    }

    private boolean hasRequiredDeposit(Customer customer, Product product, double bidAmount) {
        double totalDeposits = customer.getDeposits().stream()
                .mapToDouble(Deposit::getDepositAmount)
                .sum();

        if (totalDeposits >= (0.10 * product.getStartingPrice()) && bidAmount >= (0.10 * product.getStartingPrice())) {
            return true; // The customer has made the required deposit
        }
        return false; // The customer hasn't made the required deposit or the bid amount is insufficient
    }
}

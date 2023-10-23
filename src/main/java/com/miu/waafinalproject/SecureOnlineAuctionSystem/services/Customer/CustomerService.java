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



    public boolean isEligibleToBid(BidAddUpdateDto bidAddUpdateDTO) {
        if (bidAddUpdateDTO == null) {
            return false;
        }

        Customer customer = customerRepo.findById(bidAddUpdateDTO.getCustomerId()).orElse(null);
        Product product = productRepo.findById(bidAddUpdateDTO.getProductId()).orElse(null);

        if (customer == null || product == null) {
            return false;
        }else{
            if(product.getDeposit() == 0){
                return hasRequiredDeposit(customer, product, bidAddUpdateDTO.getNewBidAmount());
            }else{
                if(product.getDeposit() > 0 && bidAddUpdateDTO.getNewBidAmount() >= product.getDeposit()){
                    return true;
                }else{
                    return false;
                }
            }
        }


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

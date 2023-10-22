package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Bid;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.BidAddUpdateDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.enums.RolesEnum;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.CustomerCanOnlyBidExceptions;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.CustomerOrProductNotFoundException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Bid;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Customer;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Deposit;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Product;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.BidRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.CustomerRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.ProductRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidService implements IBidService{
  private final BidRepo bidRepo;
  private final CustomerRepo customerRepo;
  private final ProductRepo productRepository;
  private final CustomerService customerService;

    public Bid createBid(BidAddUpdateDto dto) {
        // Fetch the customer and product from the provided IDs
        Customer customer = customerRepo.findById(dto.getCustomerId()).orElse(null);
        Product product = productRepository.findById(dto.getProductId()).orElse(null);

        if (customer != null && product != null) {
            if (customer.getUsers().getRoles() == RolesEnum.CUSTOMER) {
                // Check if the customer meets deposit requirements, etc., here
                // You can use the hasRequiredDeposit method we discussed earlier

                Bid bid = new Bid();
                bid.setCustomer(customer);
                bid.setBidAmount(dto.getNewBidAmount());
                bid.setBidDate(new Date());
                bid.setProduct(product);
                bidRepo.save(bid);

                return bid;
            } else {
                throw new CustomerCanOnlyBidExceptions("Only customers can bid");
            }
        } else {
            // Handle cases where the customer or product with the provided IDs was not found
            throw new CustomerOrProductNotFoundException("Customer or product not found");
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
    public Bid getHighestBidder(Long productId) {
        Optional<Bid> highestBidOptional = bidRepo.findHighestBidByProductId(productId);
        return highestBidOptional.orElse(null);
    }

    public void updateBid(BidAddUpdateDto bidUpdateDTO) {
        Customer customer = customerService.getCustomerById(bidUpdateDTO.getCustomerId().intValue());
        Product product = productRepository.findById(bidUpdateDTO.getProductId()).orElse(null);

        if (customer != null && product != null && customerService.isEligibleToBid(bidUpdateDTO)) {
            Bid existingBid = bidRepo.findBidByCustomerAndProduct(customer, product);

            if (existingBid != null && bidUpdateDTO.getNewBidAmount() > existingBid.getBidAmount()) {
                existingBid.setBidAmount(bidUpdateDTO.getNewBidAmount());
                bidRepo.save(existingBid);


//                if (bidUpdateDTO.getNewBidAmount() > product.getHighestBid().getBidAmount()) {
//                    product.setHighestBid(existingBid);
//                    productRepository.save(product);
//                }
            }

        }

    }
}

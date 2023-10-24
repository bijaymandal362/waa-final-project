package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Winner;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.WinnerDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Bid;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Customer;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Product;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Winner;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.BidRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.CustomerRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.ProductRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.WinnerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WinnerService implements IWinnerService{

    private final ProductRepo productRepository;

    private final BidRepo bidRepository;

    private CustomerRepo customerRepository;

    private WinnerRepo winnerRepository;

    public void selectWinnersForProducts() {
        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            Date currentDate = new Date();
            Date bidDueDate = product.getBidDueDate();

            if (bidDueDate != null && bidDueDate.before(currentDate) && !product.isSold()) {
                WinnerDto winnerDTO = selectWinnerForProduct(product);

                // Save the winner information in the Winner repository
                Winner winner = new Winner();
                winner.setProduct(product);
                winner.setCustomer(customerRepository.findById(winnerDTO.getCustomerId()).orElse(null));
                winner.setWinningBidAmount(winnerDTO.getHighestBid());
                winnerRepository.save(winner);
            }
        }
    }

    @Override
    public List<Winner> getAllWinners() {
        return winnerRepository.findAll();
    }

    public WinnerDto selectWinnerForProduct(Product product) {
        WinnerDto winnerDTO = new WinnerDto();
        winnerDTO.setProductId(product.getProductID());
        winnerDTO.setProductName(product.getName());

        // Your logic to find the highest bid for the product
        Double highestBid = bidRepository.findHighestBidForProducts(product.getProductID());
        winnerDTO.setHighestBid(highestBid);

        // Your logic to find the customer associated with the highest bid
        Bid highestBidRecord = bidRepository.findHighestBidRecordForProduct(product.getProductID());
        if (highestBidRecord != null) {
            Customer customer = highestBidRecord.getCustomer();
            winnerDTO.setCustomerId(customer.getCustomerID());
            winnerDTO.setUsername(customer.getUsers().getUsername());
        }

        // Your logic to mark the product as sold
        product.setSold(true);
        productRepository.save(product);

        return winnerDTO;
    }
}

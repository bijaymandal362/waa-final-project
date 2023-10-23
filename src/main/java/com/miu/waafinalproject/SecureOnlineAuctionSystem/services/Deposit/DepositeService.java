package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Deposit;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.DepositDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.DepositeAddUpdateDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.CustomerOrProductNotFoundException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.InvalidDepositAmountException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Customer;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Deposit;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Product;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.CustomerRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.DepositRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositeService implements IDepositService{
    private final DepositRepo depositRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;
    @Override
    public DepositDto createDeposite(DepositeAddUpdateDto deposite) {
        Customer customer = customerRepo.findById(deposite.getCustomerId()).orElse(null);
        Product product = productRepo.findById(deposite.getProductId()).orElse(null);

        if (customer != null && product != null) {
            double depositAmount = deposite.getDepositAmount();
            double requiredDeposit = 0.10 * product.getStartingPrice();

            if (depositAmount >= requiredDeposit) {
                Deposit deposit = new Deposit();
                deposit.setRefunded(false);
                deposit.setDepositAmount(depositAmount);
                deposit.setProduct(product);
                deposit.setCustomer(customer);
                depositRepo.save(deposit);

                DepositDto depositDto = new DepositDto();
                depositDto.setAmount(deposit.getDepositAmount());
                depositDto.setCustomerId(deposit.getCustomer().getCustomerID());
                depositDto.setProductId(deposit.getProduct().getProductID());
                depositDto.setId(deposit.getDepositID());
                return  depositDto;

            } else {
                throw new InvalidDepositAmountException("Deposit amount must be at least 10% of the starting price.");
            }
        } else {
            // Handle the case where the customer or product is not found (e.g., throw an exception)
            throw new CustomerOrProductNotFoundException("Customer or product not found.");
        }
    }
}

package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Product;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.ProductDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.ProductDeleteNotAllowedException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.ProductNotFoundException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.ProductUpdateNotAllowedException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Product;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Seller;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.ProductRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.SellerRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepo productRepo;
    private final SellerRepo sellerRepo;

    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(ProductDto product) throws ProductNotFoundException {
        Seller seller = sellerRepo.findBySellerID(product.getSellerID());
        if(seller == null){
            throw new ProductNotFoundException("Seller not found with ID: " + product.getProductID());
        }
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setStartingPrice(product.getStartingPrice());
        newProduct.setDeposit(product.getDeposit());
        newProduct.setSeller(seller);
        newProduct.setSold(false);
        newProduct.setBidDueDate(product.getBidDueDate());
        newProduct.setBiddingPaymentDueDate(product.getBiddingPaymentDueDate());
        newProduct.setReleased(product.isReleased());
        return productRepo.save(newProduct);
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepo.findById(productDto.getProductID());

        if (optionalProduct.isPresent()) {
            Product existingProduct = productRepo.findById(productDto.getProductID())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            if (existingProduct.isReleased()) {
                throw new ProductUpdateNotAllowedException("Product is already released and cannot be updated.");
                //return handleReleasedProductUpdate(existingProduct, product);

            } else {


                // Update the existing product entity with data from the DTO
                existingProduct.setName(productDto.getName());
                existingProduct.setDescription(productDto.getDescription());
                existingProduct.setStartingPrice(productDto.getStartingPrice());
                existingProduct.setDeposit(productDto.getDeposit());
                existingProduct.setBidDueDate(productDto.getBidDueDate());
                existingProduct.setBiddingPaymentDueDate(productDto.getBiddingPaymentDueDate());
                existingProduct.setReleased(productDto.isReleased());

                // Retrieve the seller using the seller ID from the DTO
                Seller seller = sellerRepo.findBySellerID(productDto.getSellerID());
                if (seller == null) {
                    throw new EntityNotFoundException("Seller not found");
                }

                // Set the seller for the product
                existingProduct.setSeller(seller);

                // Save the updated product
                productRepo.save(existingProduct);
                return existingProduct;

            }
        } else {
            throw new ProductNotFoundException("Product not found with ID: " + productDto.getProductID());

        }
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException{
        Product product = productRepo.findById(id).orElse(null);
        if (product == null){
            throw new ProductNotFoundException("Product not found with ID: " + product.getProductID());
        }
        else {
            // Check if the product is released; if it's not, allow deletion
            if (!product.isReleased()) {
                productRepo.delete(product);
            } else {
                throw new ProductDeleteNotAllowedException("Product is already released and cannot be deleted.");
            }
        }
    }

    @Override
    public Optional<Product> getProductById(Long id) throws ProductNotFoundException{
        return productRepo.findById(id);
    }
}

package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Product;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.exception.ProductDeleteNotAllowedException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exception.ProductNotFoundException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exception.ProductUpdateNotAllowedException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Product;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepo productRepo;

    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException{
        Optional<Product> optionalProduct = productRepo.findById(product.getProductID());

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            if (existingProduct.isReleased()) {
                throw new ProductUpdateNotAllowedException("Product is already released and cannot be updated.");
//                return handleReleasedProductUpdate(existingProduct, product);

            } else {
                return handleNotReleasedProductUpdate(existingProduct, product);
            }
        } else {
            throw new ProductNotFoundException("Product not found with ID: " + product.getProductID());

        }
    }

//    private Product handleReleasedProductUpdate(Product existingProduct, Product updatedProduct) {
//        return productRepo.save(existingProduct);
//    }

    private Product handleNotReleasedProductUpdate(Product existingProduct, Product updatedProduct) {
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setStartingPrice(updatedProduct.getStartingPrice());
        existingProduct.setDeposit(updatedProduct.getDeposit());
        existingProduct.setBidDueDate(updatedProduct.getBidDueDate());
        existingProduct.setBiddingPaymentDueDate(updatedProduct.getBiddingPaymentDueDate());

        return productRepo.save(existingProduct);
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

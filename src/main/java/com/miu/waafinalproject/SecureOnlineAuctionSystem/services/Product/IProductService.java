package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Product;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.exception.ProductNotFoundException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> getAllProduct();
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product) throws ProductNotFoundException;
    void deleteProduct(Long id) throws ProductNotFoundException;
    Optional<Product> getProductById(Long id) throws ProductNotFoundException;


}

package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Product;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.ProductDto;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.ProductNotFoundException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> getAllProduct();
    Product createProduct(ProductDto product) throws ProductNotFoundException;
    Product updateProduct(Long id, ProductDto product) throws ProductNotFoundException;
    void deleteProduct(Long id) throws ProductNotFoundException;
    Optional<Product> getProductById(Long id) throws ProductNotFoundException;


}

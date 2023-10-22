package com.miu.waafinalproject.SecureOnlineAuctionSystem.controller;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.exception.ProductNotFoundException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Product;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.ProductRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.SellerRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private SellerRepo sellerRepository;

    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) throws ProductNotFoundException {
        return productService.updateProduct(productId, updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) throws ProductNotFoundException {
        productService.deleteProduct(productId);
    }




}

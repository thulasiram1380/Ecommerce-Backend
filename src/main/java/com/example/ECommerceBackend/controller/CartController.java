package com.example.ECommerceBackend.controller;


import com.example.ECommerceBackend.exception.ResourceNotFoundException;
import com.example.ECommerceBackend.model.Cart;
import com.example.ECommerceBackend.response.ApiResponse;
import com.example.ECommerceBackend.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cart")
public class CartController {
    private final ICartService cartService;

    @GetMapping("/{cartId}/my-cart")
    public ResponseEntity<ApiResponse> getcart(@PathVariable Long cartId){
        try{
            Cart cart = cartService.getCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Cart retrieved successfully", cart));
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId) {
        try {
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Cart cleared successfully", null));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{cartId}/cart/total-price")
    public ResponseEntity<ApiResponse> getTotalAmount(@PathVariable Long cartId) {
        try {
            BigDecimal totalPrice = cartService.getTotalPrice(cartId);
            return ResponseEntity.ok(new ApiResponse("Total price retrieved successfully", totalPrice));
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}

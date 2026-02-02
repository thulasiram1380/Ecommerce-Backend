package com.example.ECommerceBackend.controller;

import com.example.ECommerceBackend.exception.ResourceNotFoundException;
import com.example.ECommerceBackend.response.ApiResponse;
import com.example.ECommerceBackend.service.cart.CartItemService;
import com.example.ECommerceBackend.service.cart.ICartItemService;
import com.example.ECommerceBackend.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")

public class CartItemController {
    private final ICartItemService cartItemService;
    private final ICartService cartService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam(required = false) Long cartId ,
                                                     @RequestParam Long  productId ,
                                                     @RequestParam Integer quantity){
        try {
            if(cartId == null){
                cartId = cartService.initializeNewCart();
            }
            cartItemService.addItemToCart(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item added to cart successfully", null));
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId ,
                                                          @PathVariable Long itemId) {
        try {
            cartItemService.removeItemFromCart(cartId, itemId);
            return ResponseEntity.ok(new ApiResponse("Item removed from cart successfully", null));
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/cart/{cartId}/item/{productId}/update")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId ,
                                                       @PathVariable Long productId ,
                                                       @RequestParam Integer quantity){
        try {
            cartItemService.UpdateItemQuantity(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item quantity updated successfully", null));
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


}

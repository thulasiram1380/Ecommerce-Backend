package com.example.ECommerceBackend.service.cart;

import com.example.ECommerceBackend.model.CartItem;

public interface ICartItemService {



    CartItem getCartItem(Long cartId, Long productId);

    void UpdateItemQuantity(Long cartId, Long productId, int quantity);

    void addItemToCart(Long cartid, Long productId, Integer quantity);

    void removeItemFromCart(Long cartId, Long productId);
}

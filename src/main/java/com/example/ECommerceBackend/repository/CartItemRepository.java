package com.example.ECommerceBackend.repository;

import com.example.ECommerceBackend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    static void deleteAllByCartId(Long id) {
    }
}

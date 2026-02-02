package com.example.ECommerceBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CartItem> cartItems = new HashSet<>();

    // getters & setters (unchanged)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    // ✅ ADD THIS
    public void addItem(CartItem item) {
        cartItems.add(item);
        item.setCart(this);
        recalculateTotal();
    }

    // ✅ ADD THIS
    public void removeItem(CartItem item) {
        cartItems.remove(item);
        item.setCart(null);
        recalculateTotal();
    }

    // ✅ ADD THIS
    private void recalculateTotal() {
        this.totalAmount = cartItems.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}

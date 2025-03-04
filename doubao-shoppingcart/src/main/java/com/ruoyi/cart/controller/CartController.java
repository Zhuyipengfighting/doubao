package com.ruoyi.cart.controller;

import com.ruoyi.cart.entity.Cart;
import com.ruoyi.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // 添加商品到购物车
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam Integer quantity) {
        Cart cart = cartService.addToCart(userId, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    // 清空购物车
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestParam Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }

    // 获取购物车详情
    @GetMapping("/details")
    public ResponseEntity<List<Cart>> getCartDetails(@RequestParam Long userId) {
        List<Cart> cartDetails = cartService.getCartDetails(userId);
        return ResponseEntity.ok(cartDetails);
    }
}
package com.ruoyi.cart.service;


import com.ruoyi.cart.ResourceNotFoundException;
import com.ruoyi.cart.entity.Cart;
import com.ruoyi.cart.repository.CartRepository;
import com.ruoyi.goods.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    // 添加商品到购物车（数量累加）
    public Cart addToCart(Long userId, Long productId, Integer quantity) {
        // 验证商品是否存在
        productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return cartRepository.findByUserIdAndProductId(userId, productId)
                .map(existingCart -> {
                    existingCart.setQuantity(existingCart.getQuantity() + quantity);
                    return cartRepository.save(existingCart);
                })
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    newCart.setProductId(productId);
                    newCart.setQuantity(quantity);
                    return cartRepository.save(newCart);
                });
    }

    // 清空购物车
    public void clearCart(Long userId) {
        cartRepository.deleteAllByUserId(userId);
    }

    // 获取购物车详情
    public List<Cart> getCartDetails(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}


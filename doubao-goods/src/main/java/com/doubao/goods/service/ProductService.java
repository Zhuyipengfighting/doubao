package com.doubao.goods.service;

import com.doubao.goods.entity.Product;
import com.doubao.goods.repository.ProductRepository;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // 创建商品
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // 修改商品
    public Product updateProduct(Long productId, Product product) {
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (existingProduct.isPresent()) {
            product.setProductId(productId);
            return productRepository.save(product);
        }
        return null;
    }

    // 逻辑删除
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    // 查询单个商品
    public Product getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

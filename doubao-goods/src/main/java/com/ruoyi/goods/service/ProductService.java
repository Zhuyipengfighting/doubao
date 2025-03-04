package com.ruoyi.goods.service;

import org.springframework.stereotype.Service;


import com.ruoyi.goods.entity.Product;
import com.ruoyi.goods.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

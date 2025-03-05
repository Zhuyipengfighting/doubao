package com.doubao.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.doubao.order.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    List<Order> findByStatusAndCreateTimeLessThan(Integer status, Date createTime);
}
package com.doubao.order;


import com.doubao.order.Order;
import com.doubao.order.OrderItem;
import com.doubao.order.OrderItemRepository;
import com.doubao.order.OrderRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    // 创建订单
    public Order createOrder(Order order) {
        order.setCreateTime(new Date());
        order.setStatus(0); // 默认订单状态为待支付
        return orderRepository.save(order);
    }

    // 修改订单信息
    public Order updateOrder(Long orderId, Order orderDetails) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("订单不存在"));

        // 更新订单信息
        order.setTotalAmount(orderDetails.getTotalAmount());
        order.setPaymentMethod(orderDetails.getPaymentMethod());
        order.setPaymentTime(orderDetails.getPaymentTime());
        order.setCancelTime(orderDetails.getCancelTime());
        order.setUpdateTime(new Date());

        return orderRepository.save(order);
    }

    // 获取订单详情
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("订单不存在"));
    }

    // 获取用户的所有订单
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // 定时取消订单
    public void cancelExpiredOrders() {
        // 假设订单超过1小时未支付则取消
        Date oneHourAgo = new Date(System.currentTimeMillis() - 3600000);
        List<Order> expiredOrders = orderRepository.findByStatusAndCreateTimeLessThan(0, oneHourAgo);

        for (Order order : expiredOrders) {
            order.setStatus(4); // 设置订单状态为已取消
            order.setCancelTime(new Date());
            orderRepository.save(order);
        }
    }
}

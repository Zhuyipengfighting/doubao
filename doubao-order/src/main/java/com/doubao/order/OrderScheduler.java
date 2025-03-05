package com.doubao.order;

import com.doubao.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderScheduler {
    @Autowired
    private OrderService orderService;

    // 每小时检查一次
    @Scheduled(fixedRate = 3600000)
    public void cancelExpiredOrders() {
        orderService.cancelExpiredOrders();
    }
}
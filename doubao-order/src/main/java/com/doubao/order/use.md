#   创建订单：
-   请求方式：POST /api/orders
  -   请求体：{ "userId": 1, "totalAmount": 100.0, "paymentMethod": "支付宝" }
# 修改订单信息：
- 请求方式：PUT /api/orders/{orderId}
-   请求体：{ "totalAmount": 200.0, "paymentMethod": "微信" }
# 获取订单详情：
- 请求方式：GET /api/orders/{orderId}
# 获取用户的所有订单：
- 请求方式：GET /api/orders/user/{userId}
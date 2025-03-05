
- 创建商品：POST /api/products
  - 请求体：`{ "name": "string", "price": "number", "description": "string" }`
- 修改商品信息：PUT /api/products/{productId}
  - 请求体：`{ "name": "string", "price": "number", "description": "string" }`
- 删除商品：DELETE /api/products/{productId}
  
- 查询单个商品：GET /api/products/{productId}
  
- 查询批量商品：GET /api/products
  

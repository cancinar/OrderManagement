package com.cinar.orderservice.core.repository;

import domain.order.OrderDomain;

public interface PurchaseOrderRepository {

  OrderDomain create(Long productId, Long userId, Long price);

  OrderDomain findById(Long id);

  OrderDomain update(OrderDomain order);
}

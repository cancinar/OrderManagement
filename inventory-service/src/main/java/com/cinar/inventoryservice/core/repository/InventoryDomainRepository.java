package com.cinar.inventoryservice.core.repository;

import domain.inventory.enums.InventoryStatus;

public interface InventoryDomainRepository {

  InventoryStatus reserveProduct(Long orderId, Long productId, Long amount);

  void cancelProduct(Long orderId, Long productId, Long amount);

}

package com.cinar.inventoryservice.db.repository.impl;

import annotation.DomainRepository;
import com.cinar.inventoryservice.core.repository.InventoryDomainRepository;
import com.cinar.inventoryservice.db.entity.Consumption;
import com.cinar.inventoryservice.db.entity.Inventory;
import com.cinar.inventoryservice.db.repository.ConsumptionJPARepository;
import com.cinar.inventoryservice.db.repository.InventoryJPARepository;
import domain.inventory.enums.InventoryStatus;
import lombok.AllArgsConstructor;

@DomainRepository
@AllArgsConstructor
public class InventoryDomainRepositoryImpl implements InventoryDomainRepository {

  private final InventoryJPARepository inventoryJPARepository;
  private final ConsumptionJPARepository consumptionJPARepository;

  @Override
  public InventoryStatus reserveProduct(Long orderId, Long productId, Long amount) {
     return inventoryJPARepository.findByProductId(productId)
          .filter(inventory -> inventory.getAvailableInventory() >= amount)
          .map( inventory -> {
            inventory.setAvailableInventory(inventory.getAvailableInventory() - amount);
            final Inventory save = inventoryJPARepository.save(inventory);
            consumptionJPARepository.save(new Consumption(orderId, save.getProduct(), amount));

            return InventoryStatus.RESERVED;
          })
         .orElse(InventoryStatus.REJECTED);
  }

  @Override
  public void cancelProduct(Long orderId, Long productId, Long amount) {
     consumptionJPARepository.findByOrderId(orderId).ifPresent( (consumption -> {
       inventoryJPARepository.findByProductId(productId).ifPresent( inventory -> {
         inventory.setAvailableInventory(inventory.getAvailableInventory() + amount);
       });
       consumptionJPARepository.delete(consumption);
     }));
  }
}

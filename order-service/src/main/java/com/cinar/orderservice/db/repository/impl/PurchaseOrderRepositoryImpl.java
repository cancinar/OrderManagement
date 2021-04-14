package com.cinar.orderservice.db.repository.impl;

import static domain.order.enums.OrderStatus.CANCELLED;
import static domain.order.enums.OrderStatus.COMPLETED;
import static domain.order.enums.OrderStatus.CREATED;
import static exception.util.ExceptionUtil.validationException;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

import annotation.DomainRepository;
import com.cinar.orderservice.core.repository.PurchaseOrderRepository;
import com.cinar.orderservice.db.entity.PurchaseOrder;
import com.cinar.orderservice.db.repository.PurchaseOrderJPARepository;
import domain.inventory.enums.InventoryStatus;
import domain.order.OrderDomain;
import domain.payment.enums.PaymentStatus;
import lombok.AllArgsConstructor;

@DomainRepository
@AllArgsConstructor
public class PurchaseOrderRepositoryImpl implements PurchaseOrderRepository {

  private final PurchaseOrderJPARepository purchaseOrderJPARepository;

  protected final static String ORDER_NOT_FOUND = "Order could not be found!";

  @Override
  public OrderDomain create(Long productId, Long userId, Long price) {
    PurchaseOrder purchaseOrder = PurchaseOrder.builder()
        .userId(userId)
        .productId(productId)
        .price(price)
        .orderStatus(CREATED)
        .inventoryStatus(InventoryStatus.WAITING)
        .paymentStatus(PaymentStatus.WAITING)
        .build();
    return purchaseOrderJPARepository.save(purchaseOrder).toOrderDomain();
  }

  @Override
  public OrderDomain findById(Long id) {
    return find(id).toOrderDomain();
  }

  @Override
  public OrderDomain update(OrderDomain order) {
    PurchaseOrder purchaseOrder = find(order.getId());

    if (!isEmpty(order.getPaymentStatus())) {
      purchaseOrder.setPaymentStatus(order.getPaymentStatus());
    }
    if (!isEmpty(order.getInventoryStatus())) {
      purchaseOrder.setInventoryStatus(order.getInventoryStatus());
    }

    if (purchaseOrder.getPaymentStatus() == PaymentStatus.RESERVED
        && purchaseOrder.getInventoryStatus() == InventoryStatus.RESERVED) {
      purchaseOrder.setOrderStatus(COMPLETED);
    } else if (purchaseOrder.getInventoryStatus() == InventoryStatus.REJECTED
        || purchaseOrder.getPaymentStatus() == PaymentStatus.REJECTED) {
      purchaseOrder.setOrderStatus(CANCELLED);
    }

    return purchaseOrderJPARepository.save(purchaseOrder).toOrderDomain();
  }

  private PurchaseOrder find(Long id) {
    return purchaseOrderJPARepository.findById(id)
        .orElseThrow(validationException(ORDER_NOT_FOUND));
  }
}

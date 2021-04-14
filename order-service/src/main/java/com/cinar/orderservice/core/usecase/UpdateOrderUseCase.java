package com.cinar.orderservice.core.usecase;

import annotation.UseCaseComponent;
import com.cinar.orderservice.core.event.OrderStatusPublisher;
import com.cinar.orderservice.core.repository.PurchaseOrderRepository;
import com.cinar.orderservice.core.usecase.io.UpdateOrderUseCaseInput;
import com.cinar.orderservice.core.usecase.io.UpdateOrderUseCaseOutput;
import domain.inventory.enums.InventoryStatus;
import domain.order.OrderDomain;
import domain.order.enums.OrderStatus;
import domain.payment.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import usecase.UseCase;

@UseCaseComponent
@AllArgsConstructor
public class UpdateOrderUseCase implements
    UseCase<UpdateOrderUseCaseInput, UpdateOrderUseCaseOutput> {

  private final PurchaseOrderRepository purchaseOrderRepository;
  private final OrderStatusPublisher orderStatusPublisher;

  @Override
  public UpdateOrderUseCaseOutput run(UpdateOrderUseCaseInput input) {
    input.validate();
    final OrderDomain order = input.getOrder();

    input.getUpdate().accept(order);

    OrderDomain update;
    synchronized (this) {
      update = purchaseOrderRepository.update(order);
    }

    if ((order.getPaymentStatus() != PaymentStatus.ROLLED_BACK
        && order.getInventoryStatus() != InventoryStatus.ROLLED_BACK) && (order.getOrderStatus()
        != OrderStatus.COMPLETED)) {
      orderStatusPublisher.publish(update);
    }

    return new UpdateOrderUseCaseOutput(update);
  }
}

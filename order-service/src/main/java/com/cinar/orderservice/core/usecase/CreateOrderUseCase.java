package com.cinar.orderservice.core.usecase;

import annotation.UseCaseComponent;
import com.cinar.orderservice.core.event.OrderStatusPublisher;
import com.cinar.orderservice.core.repository.PurchaseOrderRepository;
import com.cinar.orderservice.core.usecase.io.CreateOrderUseCaseInput;
import com.cinar.orderservice.core.usecase.io.CreateOrderUseCaseOutput;
import domain.order.OrderDomain;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import usecase.UseCase;

@UseCaseComponent
@AllArgsConstructor
public class CreateOrderUseCase implements
    UseCase<CreateOrderUseCaseInput, CreateOrderUseCaseOutput> {

  private final PurchaseOrderRepository purchaseOrderRepository;
  private final OrderStatusPublisher orderStatusPublisher;

  @Override
  @Transactional
  public CreateOrderUseCaseOutput run(CreateOrderUseCaseInput input) {
    final OrderDomain order = purchaseOrderRepository
        .create(input.getProductId(), input.getUserId(), input.getPrice());
    orderStatusPublisher.publish(order);
    return new CreateOrderUseCaseOutput(order.getProductId(), order.getOrderStatus());
  }
}

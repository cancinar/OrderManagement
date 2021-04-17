package com.cinar.orderservice.core.usecase;

import annotation.UseCaseComponent;
import com.cinar.orderservice.core.event.OrderStatusPublisher;
import com.cinar.orderservice.core.repository.PurchaseOrderRepository;
import com.cinar.orderservice.core.usecase.io.CreateOrderUseCaseInput;
import com.cinar.orderservice.core.usecase.io.CreateOrderUseCaseOutput;
import domain.order.OrderDomain;
import event.order.OrderEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import usecase.UseCase;

@UseCaseComponent
@AllArgsConstructor
public class CreateOrderUseCase implements
    UseCase<CreateOrderUseCaseInput, CreateOrderUseCaseOutput> {

  private final PurchaseOrderRepository purchaseOrderRepository;
  private final OrderStatusPublisher orderStatusPublisher;
  @Value("${kafka.order-topic}")
  private final String orderTopic;

  @Override
  @Transactional
  public CreateOrderUseCaseOutput apply(CreateOrderUseCaseInput input) {
    final OrderDomain order = purchaseOrderRepository
        .create(input.getProductId(), input.getUserId(), input.getPrice());
    orderStatusPublisher.publish(orderTopic, new OrderEvent(order));
    return new CreateOrderUseCaseOutput(order);
  }
}

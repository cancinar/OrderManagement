package com.cinar.orderservice.core.event;

import static java.util.Optional.of;

import com.cinar.orderservice.core.usecase.FindOrderUseCase;
import com.cinar.orderservice.core.usecase.UpdateOrderUseCase;
import com.cinar.orderservice.core.usecase.io.FindOrderUseCaseInput;
import com.cinar.orderservice.core.usecase.io.UpdateOrderUseCaseInput;
import event.inventory.InventoryEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class InventoryEventConsumer {

  private final FindOrderUseCase findOrderUseCase;
  private final UpdateOrderUseCase updateOrderUseCase;

  @KafkaListener(topics = "inventory-event", groupId = "inventory")
  public void handleInventoryEvent(@Payload InventoryEvent order, Acknowledgment acknowledgment) {
    of(findOrderUseCase.run(new FindOrderUseCaseInput(order.getOrderId())).getOrder())
        .ifPresent(orderDomain -> {
          orderDomain.setInventoryStatus(order.getStatus());
          updateOrderUseCase.run(new UpdateOrderUseCaseInput(orderDomain));
          acknowledgment.acknowledge();
        });
  }
}

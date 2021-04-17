package com.cinar.inventoryservice.core.event;

import com.cinar.inventoryservice.core.usecase.CancelOrderProductUseCase;
import com.cinar.inventoryservice.core.usecase.ReserveOrderProductUseCase;
import com.cinar.inventoryservice.core.usecase.io.CancelOrderProductUseCaseInput;
import com.cinar.inventoryservice.core.usecase.io.ReserveOrderProductUseCaseInput;
import domain.inventory.enums.InventoryStatus;
import domain.order.enums.OrderStatus;
import event.inventory.InventoryEvent;
import event.order.OrderEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrderEventConsumer {

  private final ReserveOrderProductUseCase reserveOrderProductUseCase;
  private final CancelOrderProductUseCase cancelOrderProductUseCase;
  private final InventoryEventPublisher inventoryEventPublisher;
  @Value("${kafka.inventory-topic}")
  private final String inventoryTopic;

  @KafkaListener(topics = "order-event", groupId = "order")
  public void handle(@Payload OrderEvent event, Acknowledgment acknowledgment) {
    if (event.getOrder().getOrderStatus().equals(OrderStatus.CREATED)) {
      final InventoryStatus status = reserveOrderProductUseCase
          .apply(new ReserveOrderProductUseCaseInput(event.getOrder().getId(), event.getOrder()
              .getProductId(), event.getOrder().getPrice())).getStatus();

      inventoryEventPublisher
          .publish(inventoryTopic, new InventoryEvent(event.getOrder().getId(), event.getOrder()
              .getUserId(), status));

    } else if (!event.getOrder().getInventoryStatus().equals(InventoryStatus.REJECTED)) {
      cancelOrderProductUseCase
          .accept(new CancelOrderProductUseCaseInput(event.getOrder().getId(), event.getOrder()
              .getProductId(), event.getOrder().getPrice()));

      inventoryEventPublisher
          .publish(inventoryTopic, new InventoryEvent(event.getOrder().getId(), event.getOrder()
              .getUserId(), InventoryStatus.ROLLED_BACK));
    }
    acknowledgment.acknowledge();
  }
}

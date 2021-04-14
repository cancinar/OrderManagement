package com.cinar.orderservice.core.event;

import com.cinar.orderservice.core.usecase.UpdateOrderUseCase;
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

  private final UpdateOrderUseCase updateOrderUseCase;

  @KafkaListener(topics = "inventory-event", groupId = "inventory")
  public void handleInventoryEvent(@Payload InventoryEvent inventoryEvent,
      Acknowledgment acknowledgment) {
    updateOrderUseCase.run(new UpdateOrderUseCaseInput(inventoryEvent.toOrderDomain(),
        po -> po.setInventoryStatus(inventoryEvent.getStatus())));
    acknowledgment.acknowledge();
  }
}

package com.cinar.inventoryservice.core.event;

import event.inventory.InventoryEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@AllArgsConstructor
public class InventoryEventPublisher {

  private final KafkaTemplate<String, InventoryEvent> kafkaTemplate;

  public void publish(final InventoryEvent event) {
    final ListenableFuture<SendResult<String, InventoryEvent>> send = kafkaTemplate
        .send("inventory-event", event);

    send.addCallback(new ListenableFutureCallback<>() {
      @Override
      public void onFailure(Throwable throwable) {
        System.out.println("TEST");
      }

      @Override
      public void onSuccess(SendResult<String, InventoryEvent> stringOrderDomainSendResult) {
        System.out.println("TEST");

      }
    });
  }
}

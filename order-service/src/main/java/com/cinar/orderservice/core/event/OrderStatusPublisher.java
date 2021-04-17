package com.cinar.orderservice.core.event;

import event.EventPublisher;
import event.order.OrderEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@AllArgsConstructor
public class OrderStatusPublisher implements EventPublisher<OrderEvent> {

  private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

  public void publish(String topic, OrderEvent orderEvent) {
    final ListenableFuture<SendResult<String, OrderEvent>> send = kafkaTemplate
        .send("order-event", orderEvent);

    send.addCallback(new ListenableFutureCallback<>() {
      @Override
      public void onFailure(Throwable throwable) {
        System.out.println("TEST");
      }

      @Override
      public void onSuccess(SendResult<String, OrderEvent> stringOrderDomainSendResult) {
        System.out.println("TEST");

      }
    });
  }
}

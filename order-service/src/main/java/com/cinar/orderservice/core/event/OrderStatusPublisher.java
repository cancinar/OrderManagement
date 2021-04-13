package com.cinar.orderservice.core.event;

import domain.order.OrderDomain;
import event.order.OrderEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@AllArgsConstructor
public class OrderStatusPublisher {

  private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

  public void publish(final OrderDomain order) {
    final ListenableFuture<SendResult<String, OrderEvent>> send = kafkaTemplate
        .send("order-event", new OrderEvent(order));

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
    //this.orderSink.tryEmitNext(new OrderEvent(order));
  }
}

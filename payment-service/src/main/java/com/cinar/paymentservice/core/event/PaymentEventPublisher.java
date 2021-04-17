package com.cinar.paymentservice.core.event;

import event.EventPublisher;
import event.payment.PaymentEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@AllArgsConstructor
public class PaymentEventPublisher implements EventPublisher<PaymentEvent> {

  private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

  @Override
  public void publish(String topic, PaymentEvent paymentEvent) {
    final ListenableFuture<SendResult<String, PaymentEvent>> send = kafkaTemplate
        .send(topic, paymentEvent);

    send.addCallback(new ListenableFutureCallback<>() {
      @Override
      public void onFailure(Throwable throwable) {
        System.out.println("TEST");
      }

      @Override
      public void onSuccess(SendResult<String, PaymentEvent> stringOrderDomainSendResult) {
        System.out.println("TEST");

      }
    });
  }
}

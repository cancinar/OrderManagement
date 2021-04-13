package com.cinar.paymentservice.core.event;

import event.payment.PaymentEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@AllArgsConstructor
public class PaymentEventPublisher {

  private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

  public void publish(final PaymentEvent event) {
    final ListenableFuture<SendResult<String, PaymentEvent>> send = kafkaTemplate
        .send("payment-event", event);

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

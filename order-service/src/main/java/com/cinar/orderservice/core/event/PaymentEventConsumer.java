package com.cinar.orderservice.core.event;

import com.cinar.orderservice.core.usecase.UpdateOrderUseCase;
import com.cinar.orderservice.core.usecase.io.UpdateOrderUseCaseInput;
import event.payment.PaymentEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentEventConsumer {

  private final UpdateOrderUseCase updateOrderUseCase;

  @KafkaListener(topics = "payment-event", groupId = "payment")
  public void handle(@Payload PaymentEvent paymentEvent, Acknowledgment acknowledgment) {
    updateOrderUseCase.apply(new UpdateOrderUseCaseInput(paymentEvent.toOrderDomain(),
        po -> po.setPaymentStatus(paymentEvent.getStatus())));
    acknowledgment.acknowledge();
  }
}

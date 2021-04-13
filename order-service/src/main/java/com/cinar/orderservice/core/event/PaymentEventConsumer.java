package com.cinar.orderservice.core.event;

import static java.util.Optional.of;

import com.cinar.orderservice.core.usecase.FindOrderUseCase;
import com.cinar.orderservice.core.usecase.UpdateOrderUseCase;
import com.cinar.orderservice.core.usecase.io.FindOrderUseCaseInput;
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

  private final FindOrderUseCase findOrderUseCase;
  private final UpdateOrderUseCase updateOrderUseCase;

  @KafkaListener(topics = "payment-event", groupId = "payment")
  public void handle(@Payload PaymentEvent order, Acknowledgment acknowledgment) {
    of(findOrderUseCase.run(new FindOrderUseCaseInput(order.getOrderId())).getOrder())
        .ifPresent(orderDomain -> {
          orderDomain.setPaymentStatus(order.getStatus());
          updateOrderUseCase.run(new UpdateOrderUseCaseInput(orderDomain));
          acknowledgment.acknowledge();
        });
  }
}

package com.cinar.paymentservice.core.event;

import com.cinar.paymentservice.core.usecase.CancelPaymentUseCase;
import com.cinar.paymentservice.core.usecase.ReservePaymentUseCase;
import com.cinar.paymentservice.core.usecase.io.CancelPaymentUseCaseInput;
import com.cinar.paymentservice.core.usecase.io.ReservePaymentUseCaseInput;
import domain.order.enums.OrderStatus;
import domain.payment.enums.PaymentStatus;
import event.order.OrderEvent;
import event.payment.PaymentEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderEventConsumer {

  private final ReservePaymentUseCase reservePaymentUseCase;
  private final CancelPaymentUseCase cancelPaymentUseCase;
  private final PaymentEventPublisher paymentEventPublisher;
  @Value("${kafka.payment-topic}")
  private final String paymentTopic;

  @KafkaListener(topics = "order-event", groupId = "order1")
  public void handle(@Payload OrderEvent event, Acknowledgment acknowledgment) {
    if (event.getOrder().getOrderStatus().equals(OrderStatus.CREATED)) {
      final PaymentStatus status = reservePaymentUseCase
          .apply(new ReservePaymentUseCaseInput(event.getOrder().getId(), event.getOrder()
              .getProductId(), event.getOrder().getPrice())).getStatus();

      paymentEventPublisher
          .publish(paymentTopic, new PaymentEvent(event.getOrder().getId(), event.getOrder()
              .getUserId(), status));

    } else if (!event.getOrder().getPaymentStatus().equals(PaymentStatus.REJECTED)) {
      cancelPaymentUseCase
          .accept(new CancelPaymentUseCaseInput(event.getOrder().getId(), event.getOrder()
              .getProductId(), event.getOrder().getPrice()));

      paymentEventPublisher
          .publish(paymentTopic, new PaymentEvent(event.getOrder().getId(), event.getOrder()
              .getUserId(), PaymentStatus.ROLLED_BACK));
    }
    acknowledgment.acknowledge();
  }
}

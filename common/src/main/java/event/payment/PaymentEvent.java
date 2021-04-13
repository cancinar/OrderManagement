package event.payment;

import domain.payment.enums.PaymentStatus;
import event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentEvent extends Event {

  private Long orderId;
  private Long userId;
  private PaymentStatus status;
}

package domain.payment;

import domain.Domain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentDomain extends Domain {

  private Long orderId;
}

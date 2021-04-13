package domain.order;

import domain.Domain;
import domain.inventory.enums.InventoryStatus;
import domain.order.enums.OrderStatus;
import domain.payment.enums.PaymentStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderDomain extends Domain {

  private Long userId;
  private Long productId;
  private Long price;
  private OrderStatus orderStatus;
  private PaymentStatus paymentStatus;
  private InventoryStatus inventoryStatus;
}

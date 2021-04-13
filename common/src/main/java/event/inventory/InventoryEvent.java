package event.inventory;

import domain.inventory.enums.InventoryStatus;
import event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InventoryEvent extends Event {

  private Long orderId;
  private Long productId;
  private InventoryStatus status;
}

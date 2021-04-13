package com.cinar.inventoryservice.core.usecase.io;

import domain.inventory.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import usecase.Output;

@Data
@AllArgsConstructor
public class ReserveOrderProductUseCaseOutput implements Output {

  private InventoryStatus status;
}

package com.cinar.inventoryservice.core.usecase.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import usecase.Input;

@Data
@AllArgsConstructor
public class ReserveOrderProductUseCaseInput implements Input {

  private Long orderId;
  private Long productId;
  private Long amount;
}

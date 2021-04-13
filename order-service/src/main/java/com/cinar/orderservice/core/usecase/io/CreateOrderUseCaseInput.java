package com.cinar.orderservice.core.usecase.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import usecase.Input;

@Data
@AllArgsConstructor
public class CreateOrderUseCaseInput implements Input {

  private Long userId;
  private Long productId;
  private Long price;
}

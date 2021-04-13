package com.cinar.orderservice.core.usecase.io;

import domain.order.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import usecase.Output;

@Data
@AllArgsConstructor
public class CreateOrderUseCaseOutput implements Output {

  private Long productId;
  private OrderStatus status;
}

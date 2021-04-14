package com.cinar.orderservice.core.usecase.io;

import domain.order.OrderDomain;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import usecase.Input;

@Data
@AllArgsConstructor
public class UpdateOrderUseCaseInput implements Input {

  private OrderDomain order;
  private Consumer<OrderDomain> update;
}

package com.cinar.orderservice.core.usecase.io;

import domain.order.OrderDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import usecase.Output;

@Data
@AllArgsConstructor
public class UpdateOrderUseCaseOutput implements Output {

  private OrderDomain orderDomain;
}

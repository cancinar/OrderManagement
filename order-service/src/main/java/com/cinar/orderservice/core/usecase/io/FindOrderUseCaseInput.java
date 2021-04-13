package com.cinar.orderservice.core.usecase.io;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import usecase.Input;

@Data
@AllArgsConstructor
public class FindOrderUseCaseInput implements Input {

  @NotNull(message = "Order Id cannot be empty.")
  private Long orderId;
}

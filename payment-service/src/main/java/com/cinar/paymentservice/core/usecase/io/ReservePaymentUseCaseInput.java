package com.cinar.paymentservice.core.usecase.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import usecase.Input;

@Data
@AllArgsConstructor
public class ReservePaymentUseCaseInput implements Input {

  private Long orderId;
  private Long userId;
  private Long price;
}

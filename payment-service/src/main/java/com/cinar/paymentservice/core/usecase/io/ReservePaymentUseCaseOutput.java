package com.cinar.paymentservice.core.usecase.io;

import domain.payment.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import usecase.Output;

@Data
@AllArgsConstructor
public class ReservePaymentUseCaseOutput implements Output {

  private PaymentStatus status;
}

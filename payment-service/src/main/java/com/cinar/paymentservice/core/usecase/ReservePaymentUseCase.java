package com.cinar.paymentservice.core.usecase;

import annotation.UseCaseComponent;
import com.cinar.paymentservice.core.repository.UserDomainRepository;
import com.cinar.paymentservice.core.usecase.io.ReservePaymentUseCaseInput;
import com.cinar.paymentservice.core.usecase.io.ReservePaymentUseCaseOutput;
import domain.payment.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import usecase.UseCase;

@AllArgsConstructor
@UseCaseComponent
public class ReservePaymentUseCase implements
    UseCase<ReservePaymentUseCaseInput, ReservePaymentUseCaseOutput> {

  private final UserDomainRepository userDomainRepository;

  @Override
  public ReservePaymentUseCaseOutput apply(ReservePaymentUseCaseInput input) {
    final PaymentStatus paymentStatus = userDomainRepository
        .reservePayment(input.getOrderId(), input.getUserId(), input.getPrice());
    return new ReservePaymentUseCaseOutput(paymentStatus);
  }
}

package com.cinar.paymentservice.core.usecase;

import annotation.UseCaseComponent;
import com.cinar.paymentservice.core.repository.UserDomainRepository;
import com.cinar.paymentservice.core.usecase.io.CancelPaymentUseCaseInput;
import lombok.AllArgsConstructor;
import usecase.ConsumerUseCase;

@AllArgsConstructor
@UseCaseComponent
public class CancelPaymentUseCase implements
    ConsumerUseCase<CancelPaymentUseCaseInput> {

  private final UserDomainRepository userDomainRepository;

  @Override
  public void run(CancelPaymentUseCaseInput input) {
    userDomainRepository
        .cancelPayment(input.getOrderId(), input.getUserId(), input.getPrice());
  }
}

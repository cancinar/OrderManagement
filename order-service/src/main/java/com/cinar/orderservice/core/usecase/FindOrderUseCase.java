package com.cinar.orderservice.core.usecase;

import annotation.UseCaseComponent;
import com.cinar.orderservice.core.repository.PurchaseOrderRepository;
import com.cinar.orderservice.core.usecase.io.FindOrderUseCaseInput;
import com.cinar.orderservice.core.usecase.io.FindOrderUseCaseOutput;
import lombok.AllArgsConstructor;
import usecase.UseCase;

@UseCaseComponent
@AllArgsConstructor
public class FindOrderUseCase implements
    UseCase<FindOrderUseCaseInput, FindOrderUseCaseOutput> {

  private final PurchaseOrderRepository purchaseOrderRepository;

  @Override
  public FindOrderUseCaseOutput apply(FindOrderUseCaseInput input) {
    input.validate();

    return new FindOrderUseCaseOutput(purchaseOrderRepository.findById(input.getOrderId()));
  }
}

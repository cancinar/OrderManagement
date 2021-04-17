package com.cinar.inventoryservice.core.usecase;

import annotation.UseCaseComponent;
import com.cinar.inventoryservice.core.repository.InventoryDomainRepository;
import com.cinar.inventoryservice.core.usecase.io.CancelOrderProductUseCaseInput;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import usecase.ConsumerUseCase;

@UseCaseComponent
@AllArgsConstructor
public class CancelOrderProductUseCase implements
    ConsumerUseCase<CancelOrderProductUseCaseInput> {

  private final InventoryDomainRepository inventoryDomainRepository;

  @Override
  @Transactional
  public void accept(CancelOrderProductUseCaseInput input) {
    inventoryDomainRepository
        .cancelProduct(input.getOrderId(), input.getProductId(), input.getAmount());
  }
}

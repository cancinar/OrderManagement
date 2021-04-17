package com.cinar.inventoryservice.core.usecase;

import annotation.UseCaseComponent;
import com.cinar.inventoryservice.core.repository.InventoryDomainRepository;
import com.cinar.inventoryservice.core.usecase.io.ReserveOrderProductUseCaseInput;
import com.cinar.inventoryservice.core.usecase.io.ReserveOrderProductUseCaseOutput;
import domain.inventory.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import usecase.UseCase;

@UseCaseComponent
@AllArgsConstructor
public class ReserveOrderProductUseCase implements
    UseCase<ReserveOrderProductUseCaseInput, ReserveOrderProductUseCaseOutput> {

  private final InventoryDomainRepository inventoryDomainRepository;

  @Override
  public ReserveOrderProductUseCaseOutput apply(ReserveOrderProductUseCaseInput input) {
    final InventoryStatus inventoryStatus = inventoryDomainRepository
        .reserveProduct(input.getOrderId(), input.getProductId(), input.getAmount());

    return new ReserveOrderProductUseCaseOutput(inventoryStatus);
  }
}

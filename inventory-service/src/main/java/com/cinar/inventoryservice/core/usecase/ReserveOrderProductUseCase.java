package com.cinar.inventoryservice.core.usecase;

import annotation.UseCaseComponent;
import com.cinar.inventoryservice.core.repository.InventoryDomainRepository;
import com.cinar.inventoryservice.core.usecase.io.ReserveOrderProductUseCaseInput;
import com.cinar.inventoryservice.core.usecase.io.ReserveOrderProductUseCaseOutput;
import domain.inventory.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import usecase.UseCase;

@UseCaseComponent
@AllArgsConstructor
public class ReserveOrderProductUseCase implements
    UseCase<ReserveOrderProductUseCaseInput, ReserveOrderProductUseCaseOutput> {

  private final InventoryDomainRepository inventoryDomainRepository;

  @Override
  @Transactional
  public ReserveOrderProductUseCaseOutput run(ReserveOrderProductUseCaseInput input) {
    final InventoryStatus inventoryStatus = inventoryDomainRepository
        .reserveProduct(input.getOrderId(), input.getProductId(), input.getAmount());

    return new ReserveOrderProductUseCaseOutput(inventoryStatus);
  }
}

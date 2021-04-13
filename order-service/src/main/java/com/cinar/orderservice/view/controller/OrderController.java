package com.cinar.orderservice.view.controller;

import com.cinar.orderservice.core.usecase.CreateOrderUseCase;
import com.cinar.orderservice.core.usecase.io.CreateOrderUseCaseInput;
import com.cinar.orderservice.view.dto.CreateOrderDto;
import domain.order.enums.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

  private final CreateOrderUseCase createOrderUseCase;

  @PostMapping
  public ResponseEntity<OrderStatus> createOrder(@RequestBody CreateOrderDto orderDto) {
    final OrderStatus status = createOrderUseCase.run(
        new CreateOrderUseCaseInput(orderDto.getUserId(), orderDto.getProductId(),
            orderDto.getPrice()))
        .getStatus();

    return ResponseEntity.ok(status);
  }

}

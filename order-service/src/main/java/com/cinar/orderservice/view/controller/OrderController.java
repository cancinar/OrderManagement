package com.cinar.orderservice.view.controller;

import com.cinar.orderservice.core.usecase.CreateOrderUseCase;
import com.cinar.orderservice.core.usecase.io.CreateOrderUseCaseInput;
import com.cinar.orderservice.view.dto.CreateOrderDto;
import com.cinar.orderservice.view.dto.OrderDto;
import domain.order.OrderDomain;
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
  public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderDto orderDto) {
    final OrderDomain order = createOrderUseCase.apply(
        new CreateOrderUseCaseInput(orderDto.getUserId(), orderDto.getProductId(),
            orderDto.getPrice()))
        .getOrder();

    return ResponseEntity.ok(OrderDto.fromOrderDomain(order));
  }

}

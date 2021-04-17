package com.cinar.orderservice.view.dto;

import domain.order.OrderDomain;
import domain.order.enums.OrderStatus;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class OrderDto implements Serializable {

  private Long id;
  private Long productId;
  private OrderStatus status;

  public static OrderDto fromOrderDomain(OrderDomain order) {
    return OrderDto.builder()
        .id(order.getId())
        .productId(order.getProductId())
        .status(order.getOrderStatus())
        .build();
  }

}

package com.cinar.orderservice.view.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateOrderDto implements Serializable {

  private Long userId;
  private Long productId;
  private Long price;

}

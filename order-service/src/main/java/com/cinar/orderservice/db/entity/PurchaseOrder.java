package com.cinar.orderservice.db.entity;

import static javax.persistence.EnumType.STRING;

import domain.inventory.enums.InventoryStatus;
import domain.order.OrderDomain;
import domain.order.enums.OrderStatus;
import domain.payment.enums.PaymentStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "PURCHASE_ORDER")
public class PurchaseOrder extends BaseEntity {

  @Column(name = "USER_ID")
  private Long userId;

  @Column(name = "PRODUCT_ID")
  private Long productId;

  @Column(name = "PRICE")
  private Long price;

  @Enumerated(STRING)
  @Column(name = "ORDER_STATUS")
  private OrderStatus orderStatus;

  @Enumerated(STRING)
  @Column(name = "PAYMENT_STATUS")
  private PaymentStatus paymentStatus;

  @Enumerated(STRING)
  @Column(name = "INVENTORY_STATUS")
  private InventoryStatus inventoryStatus;

  @Version
  @Column(name = "VERSION")
  private int version;

  public OrderDomain toOrderDomain(){
    return OrderDomain.builder()
        .id(this.getId())
        .price(this.getPrice())
        .productId(this.getProductId())
        .userId(this.getUserId())
        .orderStatus(this.getOrderStatus())
        .paymentStatus(this.getPaymentStatus())
        .inventoryStatus(this.getInventoryStatus())
        .build();
  }
}
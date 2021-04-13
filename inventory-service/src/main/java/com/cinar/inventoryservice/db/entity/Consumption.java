package com.cinar.inventoryservice.db.entity;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONSUMPTION")
public class Consumption extends BaseEntity {

  @Column(name = "ORDER_ID", nullable = false)
  private Long orderId;

  @ManyToOne(fetch = LAZY, cascade = {PERSIST})
  @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
  private Product product;

  @Column(name = "QUANTITY", nullable = false)
  private Long quantity;
}
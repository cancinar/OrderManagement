package com.cinar.inventoryservice.db.entity;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "INVENTORY")
public class Inventory extends BaseEntity {

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
  private Product product;

  @Column(name = "AVAILABLE_INVENTORY")
  private Long availableInventory;
}
package com.cinar.paymentservice.db.entity;

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
@Table(name = "USER_TRANSACTION")
public class UserTransaction extends BaseEntity {

  @Column(name = "ORDER_ID", nullable = false)
  private Long orderId;

  @Column(name = "AMOUNT", nullable = false)
  private Long amount;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
  private User user;
}
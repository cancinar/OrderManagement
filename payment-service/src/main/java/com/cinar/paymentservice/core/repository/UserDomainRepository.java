package com.cinar.paymentservice.core.repository;

import domain.payment.enums.PaymentStatus;

public interface UserDomainRepository {

  PaymentStatus reservePayment(Long orderId, Long userId, Long price);

  void cancelPayment(Long orderId, Long userId, Long price);
}

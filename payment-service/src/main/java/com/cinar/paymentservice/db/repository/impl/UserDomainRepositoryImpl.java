package com.cinar.paymentservice.db.repository.impl;

import annotation.DomainRepository;
import com.cinar.paymentservice.core.repository.UserDomainRepository;
import com.cinar.paymentservice.db.entity.User;
import com.cinar.paymentservice.db.entity.UserTransaction;
import com.cinar.paymentservice.db.repository.UserJPARepository;
import com.cinar.paymentservice.db.repository.UserTransactionJPARepository;
import domain.payment.enums.PaymentStatus;
import lombok.AllArgsConstructor;

@DomainRepository
@AllArgsConstructor
public class UserDomainRepositoryImpl implements UserDomainRepository {

  private final UserJPARepository userJPARepository;
  private final UserTransactionJPARepository userTransactionJPARepository;

  @Override
  public PaymentStatus reservePayment(Long orderId, Long userId, Long price) {
    return userJPARepository.findById(userId)
        .filter(user -> user.getBalance() >= price)
        .map(user -> {
          user.setBalance(user.getBalance() - price);
          final User save = userJPARepository.save(user);
          userTransactionJPARepository.save(new UserTransaction(orderId, price, save));
          return PaymentStatus.RESERVED;
        })
        .orElse(PaymentStatus.REJECTED);
  }

  @Override
  public void cancelPayment(Long orderId, Long userId, Long price) {
    userTransactionJPARepository.findByOrderId(orderId).ifPresent(userTransaction -> {
      userTransactionJPARepository.delete(userTransaction);
      userJPARepository.findById(userId)
          .ifPresent(ub -> {
            ub.setBalance(ub.getBalance() + price);
            userJPARepository.save(ub);
          });
    });
  }
}

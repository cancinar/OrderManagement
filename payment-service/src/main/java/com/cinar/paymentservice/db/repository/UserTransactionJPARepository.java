package com.cinar.paymentservice.db.repository;


import com.cinar.paymentservice.db.entity.UserTransaction;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserTransactionJPARepository extends CrudRepository<UserTransaction, Long> {

  @Query("SELECT"
      + "  ut"
      + " FROM"
      + "  UserTransaction ut"
      + "  JOIN FETCH ut.user u"
      + " WHERE"
      + "  ut.orderId = :orderId")
  Optional<UserTransaction> findByOrderId(@Param("orderId") Long orderId);

}

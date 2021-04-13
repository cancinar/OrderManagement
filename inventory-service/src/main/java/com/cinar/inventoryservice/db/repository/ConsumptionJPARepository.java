package com.cinar.inventoryservice.db.repository;


import com.cinar.inventoryservice.db.entity.Consumption;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ConsumptionJPARepository extends CrudRepository<Consumption, Long> {

  @Query("SELECT"
      + "  c"
      + " FROM"
      + "  Consumption c"
      + " WHERE"
      + "  c.orderId = :orderId")
  Optional<Consumption> findByOrderId(@Param("orderId") Long orderId);

}

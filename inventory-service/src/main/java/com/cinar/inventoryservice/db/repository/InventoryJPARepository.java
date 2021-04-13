package com.cinar.inventoryservice.db.repository;


import com.cinar.inventoryservice.db.entity.Inventory;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InventoryJPARepository extends CrudRepository<Inventory, Long> {

  @Query("SELECT "
      + "  i "
      + " FROM"
      + "  Inventory i "
      + " JOIN i.product p"
      + " WHERE"
      + " p.id = :productId AND i.availableInventory >= :amount")
  Optional<Inventory> findByProductIdAndAmount(@Param("productId") Long productId,
      @Param("amount") Long amount);

  @Query("SELECT"
      + "  i"
      + " FROM"
      + "  Inventory i"
      + " JOIN FETCH i.product p"
      + " WHERE"
      + "  p.id = :productId")
  Optional<Inventory> findByProductId(@Param("productId") Long productId);
}

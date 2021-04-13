package com.cinar.orderservice.db.repository;


import com.cinar.orderservice.db.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderJPARepository extends JpaRepository<PurchaseOrder, Long> {

}

package com.cinar.inventoryservice.db.repository;


import com.cinar.inventoryservice.db.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductJPARepository extends CrudRepository<Product, Long> {

}

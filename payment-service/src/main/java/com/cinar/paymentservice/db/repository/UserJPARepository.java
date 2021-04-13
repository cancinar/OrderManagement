package com.cinar.paymentservice.db.repository;


import com.cinar.paymentservice.db.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserJPARepository extends CrudRepository<User, Long> {

}

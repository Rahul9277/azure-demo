package org.tvs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tvs.model.Customer;
import org.tvs.model.Status;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, Long> {

  @Query("{ 'status' : ?0 }")
  Page getCustomersByStatus(@Param("status") Status status, Pageable pageable);

  @Query("{ 'aadhaarNo' : ?0 }")
  Optional<Customer> getCustomerByAadharNo(@Param("aadhaarNo") String aadhaarNo);
}

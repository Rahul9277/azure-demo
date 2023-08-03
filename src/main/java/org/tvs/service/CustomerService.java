package org.tvs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.tvs.model.Role;
import org.tvs.model.Customer;

import java.util.List;


public interface CustomerService {

	Customer updateCustomer(Role role, Customer customer);

	Page getAllCustomer(Role role, Pageable pageable);

	Customer getCustomerById(long customerId);

  Customer getCustomerByAadharNo(String aadharNo);

	void deleteAll();

  void save(MultipartFile file);
}

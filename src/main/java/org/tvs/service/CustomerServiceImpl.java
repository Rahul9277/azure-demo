package org.tvs.service;

import com.mongodb.WriteConcern;
import com.mongodb.bulk.BulkWriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.tvs.exception.ResourceNotFoundException;
import org.tvs.helper.ExcelHelper;
import org.tvs.model.Customer;
import org.tvs.model.Role;
import org.tvs.model.Status;
import org.tvs.repository.CustomerRepository;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

  @Autowired
  MongoTemplate mongoTemplate;;

  @Override
  public void save(MultipartFile file) {
    try {
      bulkInsert(file);
    } catch (DuplicateKeyException ex ){
      System.out.println("Key already exist in DB. Duplicate Id or aadhar card");
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  @Override
	public Customer updateCustomer(Role role, Customer customer) {
		Optional<Customer> loadedCustomer = this.customerRepository.findById(customer.getId());
		
		if(loadedCustomer.isPresent()) {
			Customer userUpdate = loadedCustomer.get();
			userUpdate.setId(customer.getId());
			customerRepository.save(userUpdate);
			return userUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + customer.getId());
		}		
	}

	@Override
	public Page getAllCustomer(Role role, Pageable pageable) {
    switch(role) {
      case ADMIN : return this.customerRepository.findAll(pageable);
      case BOOKING: return this.customerRepository.getCustomersByStatus(Status.REDEEMED, pageable);
      case DEALER: return this.customerRepository.getCustomersByStatus(Status.BOOKED, pageable);
    }
    return Page.empty();
	}

  @Override
  public Customer getCustomerById(long customerId) {

    Optional<Customer> loadedCustomer = this.customerRepository.findById(customerId);

    if(loadedCustomer.isPresent()) {
      return loadedCustomer.get();
    }else {
      throw new ResourceNotFoundException("Record not found with id : " + customerId);
    }
  }

  @Override
  public Customer getCustomerByAadharNo(String aadharNo) {
    Optional<Customer> loadedCustomer = this.customerRepository.getCustomerByAadharNo(aadharNo);

    if(loadedCustomer.isPresent()) {
      return loadedCustomer.get();
    }else {
      throw new ResourceNotFoundException("Record not found with id : " + aadharNo);
    }
  }

  @Override
  public void deleteAll() {
    this.customerRepository.deleteAll();
  }

//  private String saveToBlob(MultipartFile file) {
//    return azureBlobAdapter.upload(file, file.getName());
//  }

  private void bulkInsert(MultipartFile file) throws IOException {
    Instant start = Instant.now();
    List<Customer> customers = ExcelHelper.excelToCustomers(file.getInputStream());
    mongoTemplate.setWriteConcern(WriteConcern.W1.withJournal(true));
    BulkOperations bulkInsertion = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Customer.class);
    //customers.forEach(customer -> bulkInsertion.insert(customers));
//    for (int i=0; i< customers.size(); ++i)
//      bulkInsertion.insert(customers.get(i));
    bulkInsertion.insert(customers);
    BulkWriteResult bulkWriteResult = bulkInsertion.execute();
    System.out.println("Bulk insert of "+bulkWriteResult.getInsertedCount()+"" +
                         "Documents completed in "+ Duration.between(start, Instant.now()).toMillis() + " milliseconds");
  }
}

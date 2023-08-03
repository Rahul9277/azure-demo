package org.tvs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.core.oidc.AddressStandardClaim;
//import org.springframework.security.oauth2.core.oidc.OidcIdToken;
//import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
//import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tvs.model.Customer;
import org.tvs.model.ImportRequest;
import org.tvs.model.Response;
import org.tvs.model.Role;
import org.tvs.service.CustomerService;
import org.tvs.helper.ExcelHelper;

import java.util.Collection;
import java.util.Map;


@RestController
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping("/loginAdmin")
  public ResponseEntity<Response> login() {
//    DefaultOidcUser principal = (DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
//    Map<String, Object> claims = principal.getClaims();
//    AddressStandardClaim address = principal.getAddress();
//    OidcIdToken idToken = principal.getIdToken();
//    OidcUserInfo userInfo = principal.getUserInfo();
//    return ResponseEntity.status(HttpStatus.OK).body(new Response("Admin Logged in Successfully", idToken.getTokenValue()));
    return null;
  }

  @PostMapping(path = "/import", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
  public ResponseEntity<Response> importCustomers(@ModelAttribute ImportRequest request) {
    String message = "";
    if (ExcelHelper.hasExcelFormat(request.getFile())) {
      try {
        customerService.save(request.getFile());
        message = "Uploaded the file successfully: " + request.getFile().getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new Response(message));
      } catch (Exception e) {
        e.printStackTrace();
        message = "Could not upload the file: " + request.getFile().getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Response(message));
      }
    }

    message = "Please upload an excel file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(message));
  }

  @GetMapping("")
  public ResponseEntity<Page> getAllCustomer(@RequestParam("pageSize") int pageSize,
                                             @RequestParam("pageNo") int pageNo) {
    Pageable pageable = PageRequest.of(pageNo, pageSize);

    return ResponseEntity.ok().body(customerService.getAllCustomer(Role.ADMIN, pageable));
  }

  @DeleteMapping
  public HttpStatus deleteAllCustomers(){
    this.customerService.deleteAll();
    return HttpStatus.OK;
  }

    @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable long id){
    return ResponseEntity.ok().body(customerService.getCustomerById(id));
  }

  @GetMapping("/aadhar/{aadharNo}")
  public ResponseEntity<Customer> getCustomerAadharNo(@PathVariable String aadharNo){
    return ResponseEntity.ok().body(customerService.getCustomerByAadharNo(aadharNo));
  }

//  @PutMapping()
//  public ResponseEntity<Response> updateCustomer(@RequestParam("file") MultipartFile file) {
//    String message = "";
//
//    if (ExcelHelper.hasExcelFormat(file)) {
//      try {
//        List<Customer> customers = customerService.save(file);
//
//        message = "Uploaded the file successfully: " + file.getOriginalFilename();
//        return ResponseEntity.status(HttpStatus.OK).body(new Response(message, customers));
//      } catch (Exception e) {
//        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Response(message));
//      }
//    }
//
//    message = "Please upload an excel file!";
//    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(message));
//    return ResponseEntity.ok().body(this.customerService.updateCustomer(Role.ADMIN, customer));
//  }



//
//  @PostMapping("")
//  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
//    return ResponseEntity.ok().body(this.customerService.createCustomer(customer));
//  }
//
}

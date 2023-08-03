package org.tvs.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Document (collection = "customerDB")
public class Customer {
	
	@Id
  private long id;

//	@NotBlank
//    @Size(max=100)
//    @Indexed(unique=true)
	private String fullName;
	private String email;
  private String mobile;
  private String fatherName;
  @Indexed(unique=true)
  private String aadhaarNo;
  private String aadharDoc;
  private String city;
  private String address;
  private String dealerName;
  private String transactionId;
  private String invoiceNo;
  private String invoiceDoc;
  private String engineNo;
  private String chasisNo;
  private String insuranceNo;
  private String insuranceDoc;
  private String registrationNo;
  private String registrationDoc;
  private String acknowledgeDoc;
  private Status status;

  @CreatedDate
  private ZonedDateTime createdAt;
  @LastModifiedDate
  private ZonedDateTime modifiedAt;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getFatherName() {
    return fatherName;
  }

  public void setFatherName(String fatherName) {
    this.fatherName = fatherName;
  }

  public String getAadhaarNo() {
    return aadhaarNo;
  }

  public void setAadhaarNo(String aadhaarNo) {
    this.aadhaarNo = aadhaarNo;
  }

  public String getAadharDoc() {
    return aadharDoc;
  }

  public void setAadharDoc(String aadharDoc) {
    this.aadharDoc = aadharDoc;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDealerName() {
    return dealerName;
  }

  public void setDealerName(String dealerName) {
    this.dealerName = dealerName;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getInvoiceNo() {
    return invoiceNo;
  }

  public void setInvoiceNo(String invoiceNo) {
    this.invoiceNo = invoiceNo;
  }

  public String getInvoiceDoc() {
    return invoiceDoc;
  }

  public void setInvoiceDoc(String invoiceDoc) {
    this.invoiceDoc = invoiceDoc;
  }

  public String getEngineNo() {
    return engineNo;
  }

  public void setEngineNo(String engineNo) {
    this.engineNo = engineNo;
  }

  public String getChasisNo() {
    return chasisNo;
  }

  public void setChasisNo(String chasisNo) {
    this.chasisNo = chasisNo;
  }

  public String getInsuranceNo() {
    return insuranceNo;
  }

  public void setInsuranceNo(String insuranceNo) {
    this.insuranceNo = insuranceNo;
  }

  public String getInsuranceDoc() {
    return insuranceDoc;
  }

  public void setInsuranceDoc(String insuranceDoc) {
    this.insuranceDoc = insuranceDoc;
  }

  public String getRegistrationNo() {
    return registrationNo;
  }

  public void setRegistrationNo(String registrationNo) {
    this.registrationNo = registrationNo;
  }

  public String getRegistrationDoc() {
    return registrationDoc;
  }

  public void setRegistrationDoc(String registrationDoc) {
    this.registrationDoc = registrationDoc;
  }

  public String getAcknowledgeDoc() {
    return acknowledgeDoc;
  }

  public void setAcknowledgeDoc(String acknowledgeDoc) {
    this.acknowledgeDoc = acknowledgeDoc;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public ZonedDateTime getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(ZonedDateTime modifiedAt) {
    this.modifiedAt = modifiedAt;
  }
}

package com.lestarieragemilang.Entities;

/**
 *  Customer Entity
 */
public class CustomerEntity {
  public CustomerEntity () {}

  /**
   *  Customer ID
   */
  private String customerId;

  // Customer ID Setter
  public void setCustomerId (String id) {
    this.customerId = id;
  }

  // Customer ID Getter
  public String getCustomerId () {
    return this.customerId;
  }



  /**
   *   Customer Name
   */
  private String customerName;

  // Customer Name Setter
  public void setCustomerName (String name) {
    this.customerName = name;
  }

  // Customer Name Getter
  public String getCustomerName () {
    return this.customerName;
  }

  

  /**
   *   Customer Contact
   */
  private String customerContact;

  // Customer Contact Setter
  public void setCustomerContact (String contact) {
    this.customerContact = contact;
  }

  // Customer Contact Getter
  public String getCustomerContact () {
    return this.customerContact;
  }



  /**
   *  Customer Address
   */
  private String customerAddress;

  // Customer Address Setter
  public void setCustomerAddress (String address) {
    this.customerAddress = address;
  }

  // Customer Address Getter
  public String getCustomerAddress () {
    return this.customerAddress;
  }



  /**
   *  Customer Email
   */
  private String customerEmail;

  // Customer Email Setter
  public void setCustomerEmail (String email) {
    this.customerEmail = email;
  }

  // Customer Email Getter
  public String getCustomerEmail () {
    return this.customerEmail;
  }
}

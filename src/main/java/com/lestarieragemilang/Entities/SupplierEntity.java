package com.lestarieragemilang.Entities;

/**
 *  Supplier Entity
 */
public class SupplierEntity {
  public SupplierEntity () {}

  /**
   *  Supplier ID
   */
  private String supplierId;

  // Supplier ID Setter
  public void setSupplierId (String id) {
    this.supplierId = id;
  }

  // Supplier ID Getter
  public String getSupplierId () {
    return this.supplierId;
  }



  /**
   *   Supplier Name
   */
  private String supplierName;

  // Supplier Name Setter
  public void setSupplierName (String name) {
    this.supplierName = name;
  }

  // Supplier Name Getter
  public String getSupplierName () {
    return this.supplierName;
  }



  /**
   *   Supplier Contact
   */
  private String supplierContact;

  // Supplier Contact Setter
  public void setSupplierContact (String contact) {
    this.supplierContact = contact;
  }

  // Supplier Contact Getter
  public String getSupplierContact () {
    return this.supplierContact;
  }



  /**
   *  Supplier Address
   */
  private String supplierAddress;

  // Supplier Address Setter
  public void setSupplierAddress (String address) {
    this.supplierAddress = address;
  }

  // Supplier Address Getter
  public String getSupplierAddress () {
    return this.supplierAddress;
  }



  /**
   *  Supplier Email
   */
  private String supplierEmail;

  // Supplier Email Setter
  public void setSupplierEmail (String email) {
    this.supplierEmail = email;
  }

  // Supplier Email Getter
  public String getSupplierEmail () {
    return this.supplierEmail;
  }
}

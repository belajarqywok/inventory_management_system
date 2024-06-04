package com.lestarieragemilang.Entities;

import java.sql.Date;


/**
 *  Purchasing Entity
 */
public class PurchasingEntity {
  public PurchasingEntity () {}

  /**
   *  Purchasing ID
   */
  private String purchasingId;

  // Purchasing ID Setter
  public void setPurchasingId (String id) {
    this.purchasingId = id;
  }

  // Purchasing ID Getter
  public String getPurchasingId () {
    return this.purchasingId;
  }


  /**
   *  Category ID
   */
  private String categoryId;

  // Category ID Setter
  public void setCategoryId(String id) {
    this.categoryId = id;
  }

  // Category ID Getter
  public String getCategoryId() {
    return this.categoryId;
  }


  /**
   *  Category Brand
   */
  private String categoryBrand;

  // Category Brand Setter
  public void setCategoryBrand(String brand) {
    this.categoryBrand = brand;
  }

  // Category Brand Getter
  public String getCategoryBrand() {
    return this.categoryBrand;
  }


  /**
   *  Category Type
   */
  private String categoryType;

  // Category Type Setter
  public void setCategoryType(String type) {
    this.categoryType = type;
  }

  // Category Type Getter
  public String getCategoryType() {
    return this.categoryType;
  }


  /**
   *  Supplier ID
   */
  private String supplierId;

  // Supplier ID Setter
  public void setSupplierId(String id) {
    this.supplierId = id;
  }

  // Supplier ID Getter
  public String getSupplierId() {
    return this.supplierId;
  }


  /**
   *  Supplier Name
   */
  private String supplierName;

  // Supplier Name Setter
  public void setSupplierName(String name) {
    this.supplierName = name;
  }

  // Supplier Name Getter
  public String getSupplierName() {
    return this.supplierName;
  }


  /**
   *  Purchasing Date
   */
  private Date purchasingDate;

  // Purchasing Date Setter
  public void setPurchasingDate(Date date) {
    this.purchasingDate = date;
  }

  // Purchasing Date Getter
  public Date getPurchasingDate() {
    return this.purchasingDate;
  }


  /**
   *  Purchasing Amount
   */
  private String purchasingAmount;

  // Purchasing Amount Setter
  public void setPurchasingAmount(String amount) {
    this.purchasingAmount = amount;
  }

  // Purchasing Amount Getter
  public String getPurchasingAmount() {
    return this.purchasingAmount;
  }


  /**
   *  Purchasing Price
   */
  private String purchasingPrice;

  // Purchasing Price Setter
  public void setPurchasingPrice(String price) {
    this.purchasingPrice = price;
  }

  // Purchasing Price Getter
  public String getPurchasingPrice() {
    return this.purchasingPrice;
  }


  /**
   *  Purchasing Total
   */
  private String purchasingTotal;

  // Purchasing Total Setter
  public void setPurchasingTotal(String total) {
    this.purchasingTotal = total;
  }

  // Purchasing Total Getter
  public String getPurchasingTotal() {
    return this.purchasingTotal;
  }
}

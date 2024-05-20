package com.lestarieragemilang.Entities;


/**
 *  Stock Entity
 */
public class StockEntity {
  public StockEntity () {}

  /**
   *  Stock ID
   */
  private String stockId;

  // Stock ID Setter
  public void setStockId(String id) {
    this.stockId = id;
  }

  // Stock ID Getter
  public String getStockId() {
    return this.stockId;
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
   *  Stock Sell Price
   */
  private String stockSellPrice;

  // Stock Sell Price Setter
  public void setStockSellPrice(String sellPrice) {
    this.stockSellPrice = sellPrice;
  }

  // Stock Sell Price Getter
  public String getStockSellPrice() {
    return this.stockSellPrice;
  }


  /**
   *  Stock Purchase Price
   */
  private String stockPurchasePrice;

  // Stock Purchase Price Setter
  public void setStockPurchasePrice(String purchasePrice) {
    this.stockPurchasePrice = purchasePrice;
  }

  // Stock Purchase Price Getter
  public String getStockPurchasePrice() {
    return this.stockPurchasePrice;
  }


  /**
   *  Stock Size
   */
  private String stockSize;

  // Stock Size Setter
  public void setStockSize(String size) {
    this.stockSize = size;
  }

  // Stock Size Getter
  public String getStockSize() {
    return this.stockSize;
  }


  /**
   *  Stock Amount
   */
  private String stockAmount;

  // Stock Amount Setter
  public void setStockAmount(String amount) {
    this.stockAmount = amount;
  }

  // Stock Amount Getter
  public String getStockAmount() {
    return this.stockAmount;
  }


  /**
   *  Stock Unit
   */
  private String stockUnit;

  // Stock Unit Setter
  public void setStockUnit(String unit) {
    this.stockUnit = unit;
  }

  // Stock Unit Getter
  public String getStockUnit() {
    return this.stockUnit;
  }
}

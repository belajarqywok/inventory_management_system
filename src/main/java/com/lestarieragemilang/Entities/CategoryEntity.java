package com.lestarieragemilang.Entities;


/**
 *  Category Entity
 */
public class CategoryEntity {
  public CategoryEntity () {}

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
  public void setCategoryBrand(String id) {
    this.categoryBrand = id;
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
   *  Category Description
   */
  private String categoryDescription;

  // Category Description Setter
  public void setCategoryDescription(String description) {
    this.categoryDescription = description;
  }

  // Category Description Getter
  public String getCategoryDescription() {
    return this.categoryDescription;
  }
}
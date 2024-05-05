package com.lestarieragemilang.Repositories;

import java.util.List;

import com.lestarieragemilang.Entities.SupplierEntity;

public class SupplierRepositoriesTest extends SupplierRepositories {
  /**
   * Get Suppliers Repository Test
   * 
   * @void
   */
  protected void getSuppliersRepositoryTest() {
    List<Object[]> getSuppliers = this
      .getSuppliersRepository();

    for (Object[] rowData : getSuppliers) {
      String supplierId      = (String) rowData[0];
      String supplierName    = (String) rowData[1];
      String supplierContact = (String) rowData[2];
      String supplierAddress = (String) rowData[3];
      String supplierEmail   = (String) rowData[4];

      System.out.println(
        String.format(
          " %s | %s | %s | %s | %s ",
          supplierId, supplierName,
          supplierContact, supplierAddress,
          supplierEmail
        )
      );
    }
  }


  /**
   * Search Suppliers Repository Test
   * 
   * @void
   */
  protected void searchSuppliersRepositoryTest() {
    List<Object[]> searchSuppliers = this
      .searchSuppliersRepository("arya");

    for (Object[] rowData : searchSuppliers) {
      String supplierId      = (String) rowData[0];
      String supplierName    = (String) rowData[1];
      String supplierContact = (String) rowData[2];
      String supplierAddress = (String) rowData[3];
      String supplierEmail   = (String) rowData[4];

      System.out.println(
        String.format(
          " %s | %s | %s | %s | %s ",
          supplierId, supplierName,
          supplierContact, supplierAddress,
          supplierEmail
        )
      );
    }
  }


  /**
   * Create Suppliers Repository Test
   * 
   * @void
   */
  protected void createSupplierRepositoryTest() {
    SupplierEntity entity = new SupplierEntity();

    entity.setSupplierId("SP-6");
    entity.setSupplierName("qywok");
    entity.setSupplierContact("+62xxxxxxxxxxx");
    entity.setSupplierAddress("new zealand");
    entity.setSupplierEmail("qywok@admin.com");

    boolean createSupplier = this
      .createSupplierRepository(entity);

    System.out.println(createSupplier);
  }


  /**
   * Update Suppliers Repository Test
   * 
   * @void
   */
  protected void updateSupplierRepositoryTest() {
    SupplierEntity entity = new SupplierEntity();

    entity.setSupplierId("SP-6");
    entity.setSupplierName("pantur silaban");
    entity.setSupplierContact("+62xxxxxxxxxxx");
    entity.setSupplierAddress("bandung");
    entity.setSupplierEmail("ptsilaban@admin.com");

    boolean updateSupplier = this
      .updateSupplierRepository(entity);

    System.out.println(updateSupplier);
  }


  /**
   * Delete Suppliers Repository Test
   * 
   * @void
   */
  protected void deleteSupplierRepositoryTest() {
    boolean deleteSupplier = this
      .deleteSupplierRepository("SP-6");

    System.out.println(deleteSupplier);
  }


  public static void main(String[] args) {
    SupplierRepositoriesTest SupplierRepos = new SupplierRepositoriesTest();

    // Get Suppliers Repository Test
    System.out.println("Get Suppliers Repository");
    SupplierRepos.getSuppliersRepositoryTest();
    System.out.println();

    // Search Suppliers Repository Test
    System.out.println("Search Suppliers Repository");
    SupplierRepos.searchSuppliersRepositoryTest();
    System.out.println();

    // Create Suppliers Repository Test
    System.out.println("Create Suppliers Repository");
    SupplierRepos.createSupplierRepositoryTest();
    System.out.println();

    // Update Suppliers Repository Test
    System.out.println("Update Suppliers Repository");
    SupplierRepos.updateSupplierRepositoryTest();
    System.out.println();

    // Delete Suppliers Repository Test
    System.out.println("Delete Suppliers Repository");
    SupplierRepos.deleteSupplierRepositoryTest();
    System.out.println();
  }
}


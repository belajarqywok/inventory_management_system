package com.lestarieragemilang.Repositories;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import com.lestarieragemilang.Entities.SupplierEntity;
import com.lestarieragemilang.Configurations.DatabaseConfiguration;


/**
 *  SupplierRepositories
 */
public class SupplierRepositories extends DatabaseConfiguration {

  // Table Name
  private final String TABLENAME = "suppliers";

  /**
   * Get Suppliers Repository
   * 
   * @return List<Object[]>
   * 
   */
  protected List<Object[]> getSuppliersRepository () {
    List<Object[]> suppliersDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT * FROM %s", this.TABLENAME
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String supplierId = resultSet
          .getString("supplier_id");
        String supplierName = resultSet
          .getString("supplier_name");
        String supplierContact = resultSet
          .getString("supplier_contact");
        String supplierAddress = resultSet
          .getString("supplier_address");
        String supplierEmail = resultSet
          .getString("supplier_email");

        Object[] rowData = {
          supplierId, supplierName,
          supplierContact, supplierAddress,
          supplierEmail
        };

        suppliersDataList.add(rowData);
      }

      return (List<Object[]>) suppliersDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return suppliersDataList;
    }
  }



  /**
   * Create Supplier Repository
   * 
   * @param entity SupplierEntity
   * @return Map<String, Object>
   * 
   */
  protected Map<String, Object> createSupplierRepository (SupplierEntity entity) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "INSERT INTO %s ( " +
        "supplier_id,      "     +
        "supplier_name,    "     +
        "supplier_contact, "     +
        "supplier_address, "     +
        "supplier_email    "     +
      ")"                        +
      "VALUES (?, ?, ?, ?, ?)", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      Object[] entities = {
        entity.getSupplierId(),
        entity.getSupplierName(),
        entity.getSupplierContact(),
        entity.getSupplierAddress(),
        entity.getSupplierEmail(),
      };
      
      for (int index = 0; index < entities.length; index ++) 
        statement.setString(index + 1, (String) entities[index]);

      if (statement.executeUpdate() > 0) {
        connection.commit();
  
        response.put("result", true);
        response.put("message", "Data Berhasil Ditambahkan.");
  
        return (Map<String, Object>) response;
            
      } else {
        connection.rollback();
  
        response.put("result", false);
        response.put("message", "Data Gagal Ditambahkan, Coba Lagi.");
  
        return (Map<String, Object>) response;
      }
  
    } catch (SQLIntegrityConstraintViolationException exception) {
      exception.printStackTrace();
  
      try { connection.rollback(); }
      catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }
  
      response.put("result", false);
      response.put("message", "ID, Email, atau Kontak Mungkin Telah Digunakan, Coba Yang Lain.");
        
      return (Map<String, Object>) response;
  
    } catch (SQLException exception) {
      exception.printStackTrace();
  
      try { connection.rollback(); }
      catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }
        
      response.put("result", false);
      response.put("message", "Data Gagal Ditambahkan, Coba Lagi.");
  
      return (Map<String, Object>) response;
  
    } finally {
      try { connection.setAutoCommit(true); }
      catch (SQLException exception) { exception.printStackTrace(); }
    }
  }



  /**
   * Update Supplier Repository
   * 
   * @param entity SupplierEntity
   * @return Map<String, Object>
   * 
   */
  protected Map<String, Object> updateSupplierRepository (SupplierEntity entity) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "UPDATE %s SET "       +
        "supplier_name      = ?, "  +
        "supplier_contact   = ?, "  +
        "supplier_address   = ?, "  +
        "supplier_email     = ?  "  +
      "WHERE supplier_id    = ?  ", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      Object[] entities = {
        entity.getSupplierName(),
        entity.getSupplierContact(),
        entity.getSupplierAddress(),
        entity.getSupplierEmail(),
        entity.getSupplierId(),
      };
      
      for (int index = 0; index < entities.length; index ++) 
        statement.setString(index + 1, (String) entities[index]);

      if (statement.executeUpdate() > 0) {
        connection.commit();
  
        response.put("result", true);
        response.put("message", "Data Berhasil Diperbarui.");
  
        return (Map<String, Object>) response;

      } else {
        connection.rollback();
  
        response.put("result", false);
        response.put("message", "Data Gagal Diperbarui, Coba Lagi.");
  
        return (Map<String, Object>) response;
      }

    } catch (SQLIntegrityConstraintViolationException exception) {
      exception.printStackTrace();
  
      try { connection.rollback(); }
      catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }
  
      response.put("result", false);
      response.put("message", "ID, Email, atau Kontak Mungkin Telah Digunakan, Coba Yang Lain.");
        
      return (Map<String, Object>) response;

    } catch (SQLException exception) {
      exception.printStackTrace();

      try { connection.rollback(); }
      catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }
      
      response.put("result", false);
      response.put("message", "Data Gagal Diperbarui, Coba Lagi.");

      return (Map<String, Object>) response;

    } finally {
      try { connection.setAutoCommit(true); }
      catch (SQLException exception) { exception.printStackTrace(); }
    }
  }



  /**
   * Delete Supplier Repository
   * 
   * @param supplierId String
   * @return boolean
   * 
   */
  protected boolean deleteSupplierRepository (String supplierId) {
    Connection connection = getConnection();
    String queryString = String.format(
      "DELETE FROM %s WHERE supplier_id = ?", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      statement.setString(1, supplierId);

      if (statement.executeUpdate() > 0) {
        connection.commit();
        return true;
        
      } else {
        connection.rollback();
        return false;
      }

    } catch (SQLException exception) {
      exception.printStackTrace();

      try { connection.rollback(); }
      catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }
      
      return false;

    } finally {
      try { 
        connection.setAutoCommit(true);
      }
      catch (SQLException exception) { exception.printStackTrace(); }
    }
  }



  /**
   * Search Suppliers Repository
   * 
   * @return List<Object[]>
   * 
   */
  protected List<Object[]> searchSuppliersRepository (String key) {
    List<Object[]> suppliersDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT * FROM %s WHERE (  " + 
        "supplier_id      LIKE '%%%s%%' OR " +
        "supplier_name    LIKE '%%%s%%' OR " +
        "supplier_contact LIKE '%%%s%%' OR " +
        "supplier_address LIKE '%%%s%%' OR " +
        "supplier_email   LIKE '%%%s%%'    " +
      ")", this.TABLENAME,
      key, key, key, key, key
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String supplierId = resultSet
          .getString("supplier_id");
        String supplierName = resultSet
          .getString("supplier_name");
        String supplierContact = resultSet
          .getString("supplier_contact");
        String supplierAddress = resultSet
          .getString("supplier_address");
        String supplierEmail = resultSet
          .getString("supplier_email");

        Object[] rowData = {
          supplierId, supplierName,
          supplierContact, supplierAddress,
          supplierEmail
        };

        suppliersDataList.add(rowData);
      }

      return (List<Object[]>) suppliersDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return suppliersDataList;
    }
  }

}

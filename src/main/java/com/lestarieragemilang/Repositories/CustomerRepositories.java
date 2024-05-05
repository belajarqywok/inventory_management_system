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

import com.lestarieragemilang.Entities.CustomerEntity;
import com.lestarieragemilang.Configurations.DatabaseConfiguration;


/**
 *  Customer Repositories
 */
public class CustomerRepositories extends DatabaseConfiguration {

  // Table Name
  private final String TABLENAME = "customers";

  /**
   * Get Customers Repository
   * 
   * @return List<Object[]>
   * 
   */
  protected List<Object[]> getCustomersRepository () {
    List<Object[]> customersDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT * FROM %s", this.TABLENAME
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String customerId = resultSet
          .getString("customer_id");
        String customerName = resultSet
          .getString("customer_name");
        String customerContact = resultSet
          .getString("customer_contact");
        String customerAddress = resultSet
          .getString("customer_address");
        String customerEmail = resultSet
          .getString("customer_email");

        Object[] rowData = {
          customerId, customerName,
          customerContact, customerAddress,
          customerEmail
        };

        customersDataList.add(rowData);
      }

      return (List<Object[]>) customersDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return customersDataList;
    }
  }



  /**
   * Create Customer Repository
   * 
   * @param entity CustomerEntity
   * @return Map<String, Object>
   * 
   */
  protected Map<String, Object> createCustomerRepository (CustomerEntity entity) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "INSERT INTO %s ( " +
        "customer_id,      "     +
        "customer_name,    "     +
        "customer_contact, "     +
        "customer_address, "     +
        "customer_email    "     +
      ")"                        +
      "VALUES (?, ?, ?, ?, ?)", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      Object[] entities = {
        entity.getCustomerId(),
        entity.getCustomerName(),
        entity.getCustomerContact(),
        entity.getCustomerAddress(),
        entity.getCustomerEmail(),
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
   * Update Customer Repository
   * 
   * @param entity CustomerEntity
   * @return Map<String, Object>
   * 
   */
  protected Map<String, Object> updateCustomerRepository (CustomerEntity entity) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "UPDATE %s SET "       +
        "customer_name      = ?, "  +
        "customer_contact   = ?, "  +
        "customer_address   = ?, "  +
        "customer_email     = ?  "  +
      "WHERE customer_id    = ?  ", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      Object[] entities = {
        entity.getCustomerName(),
        entity.getCustomerContact(),
        entity.getCustomerAddress(),
        entity.getCustomerEmail(),
        entity.getCustomerId()
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
      response.put("message", "ID, Email, dan Kontak Mungkin Telah Digunakan, Coba Yang Lain.");
      
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
   * Delete Customer Repository
   * 
   * @param customerId String
   * @return boolean
   * 
   */
  protected boolean deleteCustomerRepository (String customerId) {
    Connection connection = getConnection();
    String queryString = String.format(
      "DELETE FROM %s WHERE customer_id = ?", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      statement.setString(1, customerId);

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
   * Search Customer Repository
   * 
   * @return List<Object[]>
   * 
   */
  protected List<Object[]> searchCustomersRepository (String key) {
    List<Object[]> customersDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT * FROM %s WHERE (  " + 
        "customer_id      LIKE '%%%s%%' OR " +
        "customer_name    LIKE '%%%s%%' OR " +
        "customer_contact LIKE '%%%s%%' OR " +
        "customer_address LIKE '%%%s%%' OR " +
        "customer_email   LIKE '%%%s%%'    " +
      ")", this.TABLENAME,
      key, key, key, key, key
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String customerId = resultSet
          .getString("customer_id");
        String customerName = resultSet
          .getString("customer_name");
        String customerContact = resultSet
          .getString("customer_contact");
        String customerAddress = resultSet
          .getString("customer_address");
        String customerEmail = resultSet
          .getString("customer_email");

        Object[] rowData = {
          customerId, customerName,
          customerContact, customerAddress,
          customerEmail
        };

        customersDataList.add(rowData);
      }

      return (List<Object[]>) customersDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return customersDataList;
    }
  }

}

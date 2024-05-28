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

import com.lestarieragemilang.Entities.PurchasingEntity;
import com.lestarieragemilang.Configurations.DatabaseConfiguration;

public class PurchasingRepositories extends DatabaseConfiguration {
  
  // Table Name
  private final String TABLENAME = "purchasings";

  /**
   * Get Purchasing Repository
   * 
   * @return List<Object[]>
   * 
   */
  protected List<Object[]> getPurchasingsRepository () {
    List<Object[]> purchasingsDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT " +
        "p.purchasing_date,   " +
        "p.purchasing_id,     " +
        "p.category_id,       " +
        "c.category_brand,    " +
        "c.category_type,     " +
        "p.supplier_id,       " +
        "s.supplier_name,     " +
        "p.purchasing_amount, " +
        "p.purchasing_price,  " +
        "(p.purchasing_amount * p.purchasing_price) AS purchasing_total " +
      "FROM " +
        "purchasings p " +
      "JOIN " +
        "categories c ON p.category_id = c.category_id " +
      "JOIN " +
        "suppliers s ON p.supplier_id = s.supplier_id",
      this.TABLENAME
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String purchasingDate = resultSet
          .getString("purchasing_date");
        String purchasingId = resultSet
          .getString("purchasing_id");
        String categoryId = resultSet
          .getString("category_id");
        String categoryBrand = resultSet
          .getString("category_brand");
        String categoryType = resultSet
          .getString("category_type");
        String supplierId = resultSet
          .getString("supplier_id");
        String supplierName = resultSet
          .getString("supplier_name");
        String purchasingAmount = Integer.toString(resultSet
          .getInt("purchasing_amount"));
        String purchasingPrice = Integer.toString(resultSet
          .getInt("purchasing_price"));
        String purchasingTotal = Integer.toString(resultSet
          .getInt("purchasing_total"));

        Object[] rowData = {
          purchasingDate, purchasingId, categoryId, categoryBrand,
          categoryType, supplierId, supplierName, purchasingAmount,
          purchasingPrice, purchasingTotal
        };

        purchasingsDataList.add(rowData);
      }

      return (List<Object[]>) purchasingsDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return (List<Object[]>) purchasingsDataList;
    }
  }



  /**
   * Create Purchasing Repository
   * 
   * @param entity PurchasingEntity
   * @return Map<String, Object>
   * 
   */
  protected Map<String, Object> createPurchasingRepository (PurchasingEntity entity) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "INSERT INTO %s (        "   +
        "purchasing_id,        "   +
        "category_id,          "   +
        "supplier_id,          "   +
        "purchasing_date,      "   +
        "purchasing_amount,    "   +
        "purchasing_price      "   +
      ")"                          +
      "VALUES (?, ?, ?, ?, ?, ?, ?)", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

        Object[] entities = {
          entity.getPurchasingId(),
          entity.getCategoryId(),
          entity.getSupplierId(),

          entity.getPurchasingDate(),
          entity.getPurchasingAmount(),
          entity.getPurchasingPrice()
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
      response.put("message", "ID Pembayaran, Kategori ID, atau Supplier ID Mungkin Telah Digunakan, Coba Yang Lain.");
      
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
   * Update Purchasing Repository
   * 
   * @param entity PurchasingEntity
   * @return Map<String, Object>
   * 
   */
  protected Map<String, Object> updatePurchasingRepository (PurchasingEntity entity) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "UPDATE %s SET                " +
        "category_id           = ?, "  +
        "supplier_id           = ?, "  +
        "purchasing_date       = ?, "  +
        "purchasing_amount     = ?, "  +
        "purchasing_price      = ?  "  +
      "WHERE purchasing_id  = ? ", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

        Object[] entities = {
          entity.getCategoryId(),
          entity.getSupplierId(),

          entity.getPurchasingDate(),
          entity.getPurchasingAmount(),
          entity.getPurchasingPrice(),

          entity.getPurchasingId()
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
      response.put("message", "Kategori ID atau Supplier ID Mungkin Telah Digunakan, Coba Yang Lain.");
      
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
   * Delete Purchasing Repository
   * 
   * @param purchasingId String
   * @return boolean
   * 
   */
  protected boolean deletePurchasingRepository (String purchasingId) {
    Connection connection = getConnection();
    String queryString = String.format(
      "DELETE FROM %s WHERE purchasing_id = ?", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      statement.setString(1, purchasingId);

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
      try { connection.setAutoCommit(true); }
      catch (SQLException exception) { exception.printStackTrace(); }
    }
  }



  /**
   * Search Purchasings Repository
   * 
   * @return List<Object[]>
   * 
   */
  protected List<Object[]> searchPurchasingsRepository (String key) {
    List<Object[]> purchasingsDataList = new ArrayList<>();
    Connection connection = getConnection();

    String queryString = String.format(
      "SELECT " +
        "p.purchasing_date,   " +
        "p.purchasing_id,     " +
        "p.category_id,       " +
        "c.category_brand,    " +
        "c.category_type,     " +
        "p.supplier_id,       " +
        "s.supplier_name,     " +
        "p.purchasing_amount, " +
        "p.purchasing_price,  " +
        "(p.purchasing_amount * p.purchasing_price) AS purchasing_total " +
      "FROM  " +
        "purchasings p " +
      "JOIN  " +
        "categories c ON p.category_id = c.category_id " +
      "JOIN  " +
        "suppliers s ON p.supplier_id = s.supplier_id  " +
      "WHERE ( " +
        "p.purchasing_date    LIKE '%%%s%%' OR " +
        "p.purchasing_id      LIKE '%%%s%%' OR " +
        "p.category_id        LIKE '%%%s%%' OR " +
        "c.category_brand     LIKE '%%%s%%' OR " +
        "c.category_type      LIKE '%%%s%%' OR " +
        "p.supplier_id        LIKE '%%%s%%' OR " +
        "s.supplier_name      LIKE '%%%s%%' OR " + 
        "p.purchasing_amount  LIKE '%%%s%%' OR " + 
        "p.purchasing_price   LIKE '%%%s%%' OR " +
        "(p.purchasing_amount * p.purchasing_price) LIKE '%%%s%%'" +
      ")", this.TABLENAME,
      key, key, key, key, key,
      key, key, key, key, key
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String purchasingDate = resultSet
          .getString("purchasing_date");
        String purchasingId = resultSet
          .getString("purchasing_id");
        String categoryId = resultSet
          .getString("category_id");
        String categoryBrand = resultSet
          .getString("category_brand");
        String categoryType = resultSet
          .getString("category_type");
        String supplierId = resultSet
          .getString("supplier_id");
        String supplierName = resultSet
          .getString("supplier_name");
        String purchasingAmount = Integer.toString(resultSet
          .getInt("purchasing_amount"));
        String purchasingPrice = Integer.toString(resultSet
          .getInt("purchasing_price"));
        String purchasingTotal = Integer.toString(resultSet
          .getInt("purchasing_total"));

        Object[] rowData = {
          purchasingDate, purchasingId, categoryId, categoryBrand,
          categoryType, supplierId, supplierName, purchasingAmount,
          purchasingPrice, purchasingTotal
        };

        purchasingsDataList.add(rowData);
      }

      return (List<Object[]>) purchasingsDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return (List<Object[]>) purchasingsDataList;
    }
  }
}

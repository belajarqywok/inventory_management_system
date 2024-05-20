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

import com.lestarieragemilang.Entities.StockEntity;
import com.lestarieragemilang.Configurations.DatabaseConfiguration;



/**
 *  Stock Repositories
 */
public class StockRepositories extends DatabaseConfiguration {

  // Table Name
  private final String TABLENAME = "stocks";

  /**
   * Get Stock Repository
   * 
   * @return List<Object[]>
   * 
   */
  protected List<Object[]> getStocksRepository () {
    List<Object[]> stocksDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT * FROM %s", this.TABLENAME
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String stockId = resultSet
          .getString("stock_id");
        String categoryId = resultSet
          .getString("category_id");
        String stockSellPrice = Integer.toString(resultSet
          .getInt("stock_sell_price"));
        String stockPurchasePrice = Integer.toString(resultSet
          .getInt("stock_purchase_price"));
        String stockSize = resultSet
          .getString("stock_size");
        String stockAmount = Integer.toString(resultSet
          .getInt("stock_amount"));
        String stockUnit = resultSet
          .getString("stock_unit");

        Object[] rowData = {
          stockId, categoryId,
          stockSellPrice, stockPurchasePrice,
          stockSize, stockAmount, stockUnit
        };

        stocksDataList.add(rowData);
      }

      return (List<Object[]>) stocksDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return (List<Object[]>) stocksDataList;
    }
  }



  /**
   * Create Stock Repository
   * 
   * @param entity StockEntity
   * @return Map<String, Object>
   * 
   */
  protected Map<String, Object> createStockRepository (StockEntity entity) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "INSERT INTO %s (        "   +
        "stock_id,             "   +
        "category_id,          "   +
        "stock_sell_price,     "   +
        "stock_purchase_price, "   +
        "stock_size,           "   +
        "stock_amount,         "   +
        "stock_unit            "   +
      ")"                          +
      "VALUES (?, ?, ?, ?, ?, ?, ?)", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      Object[] entities = {
        entity.getStockId(),
        entity.getCategoryId(),

        entity.getStockSellPrice(),
        entity.getStockPurchasePrice(),

        entity.getStockSize(),
        entity.getStockAmount(),
        entity.getStockUnit()
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
      response.put("message", "ID Mungkin Telah Digunakan, Coba Yang Lain.");
      
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
   * Update Stock Repository
   * 
   * @param entity StockEntity
   * @return Map<String, Object>
   * 
   */
  protected Map<String, Object> updateStockRepository (StockEntity entity) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "UPDATE %s SET "       +
        "category_id           = ?, "  +
        "stock_sell_price      = ?, "  +
        "stock_purchase_price  = ?, "  +
        "stock_size            = ?, "  +
        "stock_amount          = ?, "  +
        "stock_unit            = ?  "  +
      "WHERE stock_id  = ? ", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

        Object[] entities = {
          entity.getCategoryId(),
  
          entity.getStockSellPrice(),
          entity.getStockPurchasePrice(),
  
          entity.getStockSize(),
          entity.getStockAmount(),
          entity.getStockUnit(),

          entity.getStockId()
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
      response.put("message", "ID Mungkin Telah Digunakan, Coba Yang Lain.");
      
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
   * Delete Stock Repository
   * 
   * @param stockId String
   * @return boolean
   * 
   */
  protected boolean deleteStockRepository (String stockId) {
    Connection connection = getConnection();
    String queryString = String.format(
      "DELETE FROM %s WHERE stock_id = ?", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      statement.setString(1, stockId);

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
   * Search Stocks Repository
   * 
   * @return List<Object[]>
   * 
   */
  protected List<Object[]> searchStocksRepository (String key) {
    List<Object[]> stocksDataList = new ArrayList<>();
    Connection connection = getConnection();

    String queryString = String.format(
      "SELECT * FROM %s WHERE (                " + 
        "stock_id             LIKE '%%%s%%' OR " +
        "category_id          LIKE '%%%s%%' OR " +
        "stock_sell_price     LIKE '%%%s%%' OR " +
        "stock_purchase_price LIKE '%%%s%%' OR " +
        "stock_size           LIKE '%%%s%%' OR " +
        "stock_amount         LIKE '%%%s%%' OR " +
        "stock_unit           LIKE '%%%s%%'    " +
      ")", this.TABLENAME,
      key, key, key, key, key, key, key
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String stockId = resultSet
          .getString("stock_id");
        String categoryId = resultSet
          .getString("category_id");
        String stockSellPrice = Integer.toString(resultSet
          .getInt("stock_sell_price"));
        String stockPurchasePrice = Integer.toString(resultSet
          .getInt("stock_purchase_price"));
        String stockSize = resultSet
          .getString("stock_size");
        String stockAmount = Integer.toString(resultSet
          .getInt("stock_amount"));
        String stockUnit = resultSet
          .getString("stock_unit");

        Object[] rowData = {
          stockId, categoryId,
          stockSellPrice, stockPurchasePrice,
          stockSize, stockAmount, stockUnit
        };

        stocksDataList.add(rowData);
      }

      return (List<Object[]>) stocksDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return (List<Object[]>) stocksDataList;
    }
  }
}

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

import com.lestarieragemilang.Entities.CategoryEntity;
import com.lestarieragemilang.Configurations.DatabaseConfiguration;



/**
 *  Category Repositories
 */
public class CategoryRepositories extends DatabaseConfiguration {

  // Table Name
  private final String TABLENAME = "categories";

  /**
   * Get Category Repository
   * 
   * @return List<Object[]>
   * 
   */
  protected List<Object[]> getCategoriesRepository () {
    List<Object[]> categoriesDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT * FROM %s", this.TABLENAME
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String categoryId = resultSet
          .getString("category_id");
        String categoryBrand = resultSet
          .getString("category_brand");
        String categoryType = resultSet
          .getString("category_type");
        String categoryDescription = resultSet
          .getString("category_desc");

        Object[] rowData = {
          categoryId, categoryBrand,
          categoryType, categoryDescription
        };

        categoriesDataList.add(rowData);
      }

      return (List<Object[]>) categoriesDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return categoriesDataList;
    }
  }



  /**
   * Create Category Repository
   * 
   * @param entity CategoryEntity
   * @return Map<String, Object>
   * 
   */
  protected Map<String, Object> createCategoryRepository (CategoryEntity entity) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "INSERT INTO %s (    "  +
        "category_id,      "  +
        "category_brand,   "  +
        "category_type,    "  +
        "category_desc     "  +
      ")"                     +
      "VALUES (?, ?, ?, ?)", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      Object[] entities = {
        entity.getCategoryId(),
        entity.getCategoryBrand(),
        entity.getCategoryType(),
        entity.getCategoryDescription()
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
   * Update Category Repository
   * 
   * @param entity CategoryEntity
   * @return Map<String, Object>
   * 
   */
  protected Map<String, Object> updateCategoryRepository (CategoryEntity entity) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "UPDATE %s SET "              +
        "category_brand     = ?, "  +
        "category_type      = ?, "  +
        "category_desc      = ? "  +
      "WHERE category_id    = ?  ", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

        Object[] entities = {
          entity.getCategoryBrand(),
          entity.getCategoryType(),
          entity.getCategoryDescription(),
          entity.getCategoryId()
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
   * Delete Category Repository
   * 
   * @param categoryId String
   * @return boolean
   * 
   */
  protected boolean deleteCategoryRepository (String categoryId) {
    Connection connection = getConnection();
    String queryString = String.format(
      "DELETE FROM %s WHERE category_id = ?", this.TABLENAME
    );
        
    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      statement.setString(1, categoryId);

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
   * Search Category Repository
   * 
   * @return List<Object[]>
   * 
   */
  protected List<Object[]> searchCategoriesRepository (String key) {
    List<Object[]> categoriesDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT * FROM %s WHERE (  " + 
        "category_id      LIKE '%%%s%%' OR " +
        "category_brand   LIKE '%%%s%%' OR " +
        "category_type    LIKE '%%%s%%' OR " +
        "category_desc    LIKE '%%%s%%'    " +
      ")", this.TABLENAME,
      key, key, key, key
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String categoryId = resultSet
          .getString("category_id");
        String categoryBrand = resultSet
          .getString("category_brand");
        String categoryType = resultSet
          .getString("category_type");
        String categoryDescription = resultSet
          .getString("category_desc");

        Object[] rowData = {
          categoryId, categoryBrand,
          categoryType, categoryDescription
        };

        categoriesDataList.add(rowData);
      }

      return (List<Object[]>) categoriesDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return categoriesDataList;
    }
  }

}
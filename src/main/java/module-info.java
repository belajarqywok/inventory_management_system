module com.lestarieragemilang {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;
  requires java.desktop;
  requires transitive javafx.graphics;

  opens com.lestarieragemilang to javafx.fxml;
  opens com.lestarieragemilang.Entities to javafx.base;
  
  exports com.lestarieragemilang;
}
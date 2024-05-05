/*
    visual programming assignment - group 1

    -- db schemas --

*/

BEGIN
  EXECUTE IMMEDIATE 'DROP DATABASE IF EXISTS iron_sales_db';
  EXECUTE IMMEDIATE 'CREATE DATABASE IF NOT EXISTS iron_sales_db';
  EXECUTE IMMEDIATE 'USE iron_sales_db';

  /*
    create admin auth table
    design ref: login
    default => user: admin; pass: user246
  */
  DECLARE
    username VARCHAR2(16) := 'admin';
    password VARCHAR2(512) := DBMS_CRYPTO.HASH('user246', DBMS_CRYPTO.HASH_SH256);
  BEGIN
    EXECUTE IMMEDIATE '
      CREATE TABLE admin_auth (
        auth_meta_id   VARCHAR2(36) DEFAULT SYS_GUID() PRIMARY KEY,
        auth_username  VARCHAR2(16) UNIQUE NOT NULL,
        auth_password  VARCHAR2(512) NOT NULL
      )
    ';
    EXECUTE IMMEDIATE 'CREATE INDEX idx_auth_username ON admin_auth (auth_username)';
    EXECUTE IMMEDIATE 'CREATE INDEX idx_auth_password ON admin_auth (auth_password)';
    EXECUTE IMMEDIATE '
      INSERT INTO admin_auth (auth_username, auth_password)
      SELECT :1, :2 FROM DUAL
      WHERE NOT EXISTS (
          SELECT 1 FROM admin_auth
          WHERE auth_username = :1
          AND auth_password   = :2
      )
    ' USING username, password;
    EXECUTE IMMEDIATE 'COMMIT';
  END;

  /*
    create category table
    design ref: kategori
  */
  EXECUTE IMMEDIATE '
    CREATE TABLE categories (
      category_id       VARCHAR2(16) NOT NULL PRIMARY KEY,
      category_brand    VARCHAR2(32) NOT NULL,
      category_type     VARCHAR2(32) NOT NULL,
      category_desc     CLOB NOT NULL
    )
  ';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_category_brand ON categories (category_brand)';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_category_type ON categories (category_type)';

  /*
    create supplier table
    design ref: supplier
  */
  EXECUTE IMMEDIATE '
    CREATE TABLE suppliers (
      supplier_id       VARCHAR2(16) NOT NULL PRIMARY KEY,
      supplier_name     VARCHAR2(32) NOT NULL,
      supplier_contact  VARCHAR2(16) UNIQUE NOT NULL,
      supplier_address  CLOB NOT NULL,
      supplier_email    VARCHAR2(16) UNIQUE NOT NULL
    )
  ';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_supplier_name ON suppliers (supplier_name)';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_supplier_contact ON suppliers (supplier_contact)';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_supplier_email ON suppliers (supplier_email)';

  /*
    create customer table
    design ref: pelanggan
  */
  EXECUTE IMMEDIATE '
    CREATE TABLE customers (
      customer_id       VARCHAR2(16) NOT NULL PRIMARY KEY,
      customer_name     VARCHAR2(32) NOT NULL,
      customer_contact  VARCHAR2(16) NOT NULL,
      customer_address  CLOB NOT NULL,
      customer_email    VARCHAR2(16) NOT NULL
    )
  ';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_customer_name ON customers (customer_name)';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_customer_contact ON customers (customer_contact)';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_customer_email ON customers (customer_email)';

  /*
    create purchasing table
    design ref: pembelian
  */
  EXECUTE IMMEDIATE '
    CREATE TABLE purchasings (
      purchasing_id      VARCHAR2(16) NOT NULL PRIMARY KEY,
      category_id        VARCHAR2(16) NOT NULL,
      supplier_id        VARCHAR2(16) NOT NULL,
      purchasing_date    DATE NOT NULL,
      purchasing_amount  NUMBER NOT NULL,
      purchasing_price   NUMBER NOT NULL,
      CONSTRAINT fk_category
        FOREIGN KEY (category_id)
        REFERENCES categories(category_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
      CONSTRAINT fk_supplier
        FOREIGN KEY (supplier_id)
        REFERENCES suppliers(supplier_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
    )
  ';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_purchasing_date ON purchasings (purchasing_date)';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_purchasing_amount ON purchasings (purchasing_amount)';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_purchasing_price ON purchasings (purchasing_price)';

  /*
    create reception table
    design ref: penerimaan
  */
  EXECUTE IMMEDIATE '
    CREATE TABLE receptions (
      reception_id         VARCHAR2(16) NOT NULL PRIMARY KEY,
      category_id          VARCHAR2(16) NOT NULL,
      reception_date       DATE NOT NULL,
      reception_amount     NUMBER NOT NULL,
      reception_condition  CLOB NOT NULL,
      CONSTRAINT fk_category
        FOREIGN KEY (category_id)
        REFERENCES categories(category_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
    )
  ';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_reception_date ON receptions (reception_date)';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_reception_condition ON receptions (reception_condition)';

  /*
    create stock table
    design ref: stok
  */
  EXECUTE IMMEDIATE '
    CREATE TABLE stocks (
      stock_id               VARCHAR2(16) NOT NULL PRIMARY KEY,
      category_id            VARCHAR2(16) NOT NULL,
      stock_sell_price       NUMBER NOT NULL,
      stock_purchace_price   NUMBER NOT NULL,
      stock_size             VARCHAR2(16) NOT NULL,
      stock_amount           NUMBER NOT NULL,
      stock_unit             VARCHAR2(16) NOT NULL,
      stock_image            VARCHAR2(64) NOT NULL,
      CONSTRAINT fk_category
        FOREIGN KEY (category_id)
        REFERENCES categories(category_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
    )
  ';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_stock_size ON stocks (stock_size)';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_stock_sell_price ON stocks (stock_sell_price)';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_stock_purchace_price ON stocks (stock_purchace_price)';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_stock_amount ON stocks (stock_amount)';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_stock_unit ON stocks (stock_unit)';

  /*
    create sales table
    design ref: penjualan
  */
  EXECUTE IMMEDIATE '
    CREATE TABLE sales (
      sales_id      VARCHAR2(16) NOT NULL PRIMARY KEY,
      stock_id      VARCHAR2(16) NOT NULL,
      customer_id   VARCHAR2(16) NOT NULL,
      sales_date    DATE NOT NULL,
      sales_amount  NUMBER NOT NULL,
      CONSTRAINT fk_stock
        FOREIGN KEY (stock_id)
        REFERENCES stocks (stock_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
      CONSTRAINT fk_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers (customer_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
    )
  ';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_sales_date ON sales (sales_date)';
  EXECUTE IMMEDIATE 'CREATE INDEX idx_sales_amount ON sales (sales_amount)';
END;
/

/*

    Visual Programming Assignment - Group 1

    -- DB Schemas --

*/


-- Database Configuration
DROP DATABASE IF EXISTS iron_sales_db;
CREATE DATABASE IF NOT EXISTS iron_sales_db;

USE iron_sales_db;



/*
    Create Admin Auth Table
    design ref: Login
    default => user: admin; pass: user246
*/
SET @username = "admin";
SET @password = "user246";

CREATE TABLE admin_auth (
  auth_meta_id   VARCHAR(36)  DEFAULT (UUID()) PRIMARY KEY,

  auth_username  VARCHAR(16)  UNIQUE NOT NULL,
  auth_password  VARCHAR(512) NOT NULL
);

CREATE INDEX idx_auth_username
  ON admin_auth (auth_username);

CREATE INDEX idx_auth_password 
  ON admin_auth (auth_password);

INSERT INTO admin_auth (auth_username, auth_password)
SELECT * FROM (SELECT @username, @password) AS _tmp
WHERE NOT EXISTS (
    SELECT 1 FROM admin_auth
    WHERE auth_username = @username 
    AND auth_password   = @password
);

DESCRIBE admin_auth; SELECT " ";



/*
    Create Category Table
    design ref: Kategori
*/
CREATE TABLE categories (
  category_id       VARCHAR(16) NOT NULL PRIMARY KEY,

  category_brand    VARCHAR(32) NOT NULL,
  category_type     VARCHAR(32) NOT NULL,
  category_desc     TEXT NOT NULL
);

CREATE INDEX idx_category_brand
  ON categories (category_brand);

CREATE INDEX idx_category_type
  ON categories (category_type);

DESCRIBE categories; SELECT " ";




/*
    Create Supplier Table
    design ref: Supplier
*/
CREATE TABLE suppliers (
  supplier_id       VARCHAR(16) NOT NULL PRIMARY KEY,

  supplier_name     VARCHAR(32) NOT NULL,
  supplier_contact  VARCHAR(32) UNIQUE NOT NULL,
  supplier_address  TEXT NOT NULL,
  supplier_email    VARCHAR(32) UNIQUE NOT NULL
);

CREATE INDEX idx_supplier_name
  ON suppliers (supplier_name);

CREATE INDEX idx_supplier_contact
  ON suppliers (supplier_contact);

CREATE INDEX idx_supplier_email
  ON suppliers (supplier_email);

DESCRIBE suppliers; SELECT " ";



/*
    Create Customer Table
    design ref: Pelanggan
*/
CREATE TABLE customers (
  customer_id       VARCHAR(16) NOT NULL PRIMARY KEY,

  customer_name     VARCHAR(32) NOT NULL,
  customer_contact  VARCHAR(16) NOT NULL,
  customer_address  TEXT NOT NULL,
  customer_email    VARCHAR(16) NOT NULL
);

CREATE INDEX idx_customer_name
  ON customers (customer_name);

CREATE INDEX idx_customer_contact
  ON customers (customer_contact);

CREATE INDEX idx_customer_email
  ON customers (customer_email);

DESCRIBE customers; SELECT " ";



/*
    Create Purchasing Table
    design ref: Pembelian
*/
CREATE TABLE purchasings (
  purchasing_id      VARCHAR(16) NOT NULL PRIMARY KEY,
  category_id        VARCHAR(16) NOT NULL,
  supplier_id        VARCHAR(16) NOT NULL,

  purchasing_date    DATE    NOT NULL,
  purchasing_amount  INT     NOT NULL,
  purchasing_price   BIGINT  NOT NULL,

  FOREIGN KEY (category_id)
  REFERENCES categories(category_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

  FOREIGN KEY (supplier_id)
  REFERENCES suppliers(supplier_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE INDEX idx_purchasing_date
  ON purchasings (purchasing_date);

CREATE INDEX idx_purchasing_amount
  ON purchasings (purchasing_amount);

CREATE INDEX idx_purchasing_price
  ON purchasings (purchasing_price);

DESCRIBE purchasings; SELECT " ";



/*
    Create Reception Table
    design ref: Penerimaan
*/
CREATE TABLE receptions (
  reception_id         VARCHAR(16) NOT NULL PRIMARY KEY,
  category_id          VARCHAR(16) NOT NULL,

  reception_date       DATE NOT NULL,
  reception_amount     INT NOT NULL,
  reception_condition  TEXT NOT NULL,

  FOREIGN KEY (category_id)
  REFERENCES categories(category_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE INDEX idx_reception_date 
  ON receptions (reception_date);

CREATE INDEX idx_reception_condition
  ON receptions (reception_condition);

DESCRIBE receptions; SELECT " ";



/*
    Create Stock Table
    design ref: Stok
*/
CREATE TABLE stocks (
  stock_id               VARCHAR(16) NOT NULL PRIMARY KEY,
  category_id            VARCHAR(16) NOT NULL,

  stock_sell_price       BIGINT NOT NULL,
  stock_purchace_price   BIGINT NOT NULL,

  stock_size             VARCHAR(16)   NOT NULL,
  stock_amount           INT          NOT NULL,
  stock_unit             VARCHAR(16)  NOT NULL,
  stock_image            VARCHAR(64) NOT NULL,

  FOREIGN KEY (category_id)
  REFERENCES categories(category_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE INDEX idx_stock_size
  ON stocks (stock_size);

CREATE INDEX idx_stock_sell_price
  ON stocks (stock_sell_price);

CREATE INDEX idx_stock_purchace_price
  ON stocks (stock_purchace_price);

CREATE INDEX idx_stock_amount
  ON stocks (stock_amount);

CREATE INDEX idx_stock_unit
  ON stocks (stock_unit);

DESCRIBE stocks; SELECT " ";



/*
    Create Sales Table
    design ref: Penjualan
*/
CREATE TABLE sales (
  sales_id      VARCHAR(16) NOT NULL PRIMARY KEY,
  stock_id      VARCHAR(16) NOT NULL,
  customer_id   VARCHAR(16) NOT NULL,

  sales_date    DATE NOT NULL,
  sales_amount  INT  NOT NULL,

  FOREIGN KEY (stock_id)
  REFERENCES stocks (stock_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

  FOREIGN KEY (customer_id)
  REFERENCES customers (customer_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE INDEX idx_sales_date 
  ON sales (sales_date);

CREATE INDEX idx_sales_amount
  ON sales (sales_amount);

DESCRIBE sales; SELECT " ";



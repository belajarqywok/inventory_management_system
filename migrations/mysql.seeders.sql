/*

    Visual Programming Assignment - Group 1

    -- DB Seeders --

*/

USE iron_sales_db;

-- Insert Categories Datas
INSERT INTO categories (
  category_id, 

  category_brand,
  category_type,
  category_desc
) VALUES
("CT-1", "Krakatau Steel", "Baja Ringan", "Besi bagus untuk membangun apa saja."),
("CT-2", "Rheinmetal", "Besi Tahan Karat", "Besi bagus untuk membangun apa saja."),
("CT-3", "Baojing", "Besi Fondasi", "Besi bagus untuk membangun apa saja."),
("CT-4", "Krakatau Steel", "Besi Beton", "Besi bagus untuk membangun apa saja."),
("CT-5", "Rheinmetal", "Besi Tempa", "Besi bagus untuk membangun apa saja.");

-- Insert Supplier Datas
INSERT INTO suppliers (
  supplier_id,

  supplier_name,
  supplier_contact,
  supplier_address,
  supplier_email
) VALUES
("SP-1", "feri", "+62918391219231", "blabalbalablablablab", "1@admin.com"),
("SP-2", "rio", "+62918391219232", "blabalbalablablablab", "2@admin.com"),
("SP-3", "nanda", "+62918391219233", "blabalbalablablablab", "3@admin.com"),
("SP-4", "gondes", "+62918391219234", "blabalbalablablablab", "4@admin.com"),
("SP-5", "arya", "+62918391219235", "blabalbalablablablab", "5@admin.com");

-- Insert Customer Datas
INSERT INTO customers (
  customer_id,

  customer_name,
  customer_contact,
  customer_address,
  customer_email
) VALUES
("CM-1", "rizan", "+62918391219231", "blabalbalablablablab", "1@admin.com"),
("CM-2", "wira", "+62918391219232", "blabalbalablablablab", "2@admin.com"),
("CM-3", "qywok", "+62918391219233", "blabalbalablablablab", "3@admin.com"),
("CM-4", "tania", "+62918391219234", "blabalbalablablablab", "4@admin.com"),
("CM-5", "michelle", "+62918391219235", "blabalbalablablablab", "5@admin.com"),
("CM-6", "mikael", "+62918391219236", "blabalbalablablablab", "6@admin.com");

-- Insert Purchasing Datas
INSERT INTO purchasings (
  purchasing_id,
  category_id,
  supplier_id,

  purchasing_date,
  purchasing_amount,
  purchasing_price
) VALUES
("PH-1", "CT-2", "SP-3", "2024-04-25", 10, 100000),
("PH-2", "CT-2", "SP-3", "2024-04-25", 10, 100000),
("PH-3", "CT-2", "SP-3", "2024-04-25", 10, 100000),
("PH-4", "CT-2", "SP-3", "2024-04-25", 10, 100000),
("PH-5", "CT-2", "SP-3", "2024-04-25", 10, 100000);

-- Insert Stocks Datas
INSERT INTO stocks (
  stock_id,
  category_id,

  stock_sell_price,
  stock_purchase_price,

  stock_size,
  stock_amount,
  stock_unit
) VALUES
("STK-1", "CT-1", 150000, 120000, "10 mm", 50, "batang"),
("STK-2", "CT-1", 160000, 130000, "10 mm", 40, "batang"),
("STK-3", "CT-1", 170000, 140000, "10 mm", 30, "batang"),
("STK-4", "CT-1", 180000, 150000, "10 mm", 20, "batang"),
("STK-5", "CT-1", 190000, 160000, "10 mm", 15, "batang");

-- Insert Sales Datas
INSERT INTO sales (
  sales_id,
  stock_id,
  customer_id,

  sales_date,
  sales_amount
) VALUES
("SAL-1", "STK-1", "CM-1", "2024-04-25", 5),
("SAL-2", "STK-2", "CM-2", "2024-04-26", 3),
("SAL-3", "STK-3", "CM-3", "2024-04-27", 8),
("SAL-4", "STK-4", "CM-4", "2024-04-28", 2),
("SAL-5", "STK-5", "CM-5", "2024-04-29", 6);

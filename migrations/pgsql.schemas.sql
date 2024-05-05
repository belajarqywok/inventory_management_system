/*
    visual programming assignment - group 1

    -- db schemas --

*/


-- database configuration
drop database if exists iron_sales_db;
create database if not exists iron_sales_db;

\c iron_sales_db;



/*
    create admin auth table
    design ref: login
    default => user: admin; pass: user246
*/
DO $$
DECLARE
    username TEXT := 'admin';
    password TEXT := digest('user246', 'sha256');
BEGIN
    CREATE TABLE IF NOT EXISTS admin_auth (
        auth_meta_id   UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
        auth_username  VARCHAR(16) UNIQUE NOT NULL,
        auth_password  VARCHAR(512) NOT NULL
    );

    CREATE INDEX IF NOT EXISTS idx_auth_username ON admin_auth (auth_username);
    CREATE INDEX IF NOT EXISTS idx_auth_password ON admin_auth (auth_password);

    INSERT INTO admin_auth (auth_username, auth_password)
    SELECT * FROM (SELECT username, password) AS tmp
    WHERE NOT EXISTS (
        SELECT 1 FROM admin_auth
        WHERE auth_username = username
        AND auth_password   = password
    );
END $$;

SELECT * FROM information_schema.tables WHERE table_name = 'admin_auth';




/*
    create category table
    design ref: kategori
*/
CREATE TABLE IF NOT EXISTS categories (
    category_id       VARCHAR(16) NOT NULL PRIMARY KEY,
    category_brand    VARCHAR(32) NOT NULL,
    category_type     VARCHAR(32) NOT NULL,
    category_desc     TEXT NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_category_brand ON categories (category_brand);
CREATE INDEX IF NOT EXISTS idx_category_type ON categories (category_type);

SELECT * FROM information_schema.tables WHERE table_name = 'categories';




/*
    create supplier table
    design ref: supplier
*/
CREATE TABLE IF NOT EXISTS suppliers (
    supplier_id       VARCHAR(16) NOT NULL PRIMARY KEY,
    supplier_name     VARCHAR(32) NOT NULL,
    supplier_contact  VARCHAR(16) UNIQUE NOT NULL,
    supplier_address  TEXT NOT NULL,
    supplier_email    VARCHAR(16) UNIQUE NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_supplier_name ON suppliers (supplier_name);
CREATE INDEX IF NOT EXISTS idx_supplier_contact ON suppliers (supplier_contact);
CREATE INDEX IF NOT EXISTS idx_supplier_email ON suppliers (supplier_email);

SELECT * FROM information_schema.tables WHERE table_name = 'suppliers';




/*
    create customer table
    design ref: pelanggan
*/
CREATE TABLE IF NOT EXISTS customers (
    customer_id       VARCHAR(16) NOT NULL PRIMARY KEY,
    customer_name     VARCHAR(32) NOT NULL,
    customer_contact  VARCHAR(16) NOT NULL,
    customer_address  TEXT NOT NULL,
    customer_email    VARCHAR(16) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_customer_name ON customers (customer_name);
CREATE INDEX IF NOT EXISTS idx_customer_contact ON customers (customer_contact);
CREATE INDEX IF NOT EXISTS idx_customer_email ON customers (customer_email);

SELECT * FROM information_schema.tables WHERE table_name = 'customers';




/*
    create purchasing table
    design ref: pembelian
*/
CREATE TABLE IF NOT EXISTS purchasings (
    purchasing_id      VARCHAR(16) NOT NULL PRIMARY KEY,
    category_id        VARCHAR(16) NOT NULL,
    supplier_id        VARCHAR(16) NOT NULL,
    purchasing_date    DATE NOT NULL,
    purchasing_amount  INT NOT NULL,
    purchasing_price   BIGINT NOT NULL,
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
);

CREATE INDEX IF NOT EXISTS idx_purchasing_date ON purchasings (purchasing_date);
CREATE INDEX IF NOT EXISTS idx_purchasing_amount ON purchasings (purchasing_amount);
CREATE INDEX IF NOT EXISTS idx_purchasing_price ON purchasings (purchasing_price);

SELECT * FROM information_schema.tables WHERE table_name = 'purchasings';




/*
    create reception table
    design ref: penerimaan
*/
CREATE TABLE IF NOT EXISTS receptions (
    reception_id         VARCHAR(16) NOT NULL PRIMARY KEY,
    category_id          VARCHAR(16) NOT NULL,
    reception_date       DATE NOT NULL,
    reception_amount     INT NOT NULL,
    reception_condition  TEXT NOT NULL,
    CONSTRAINT fk_category
        FOREIGN KEY (category_id)
        REFERENCES categories(category_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_reception_date ON receptions (reception_date);
CREATE INDEX IF NOT EXISTS idx_reception_condition ON receptions (reception_condition);

SELECT * FROM information_schema.tables WHERE table_name = 'receptions';




/*
    create stock table
    design ref: stok
*/
CREATE TABLE IF NOT EXISTS stocks (
    stock_id               VARCHAR(16) NOT NULL PRIMARY KEY,
    category_id            VARCHAR(16) NOT NULL,
    stock_sell_price       BIGINT NOT NULL,
    stock_purchace_price   BIGINT NOT NULL,
    stock_size             VARCHAR(16) NOT NULL,
    stock_amount           INT NOT NULL,
    stock_unit             VARCHAR(16) NOT NULL,
    stock_image            VARCHAR(64) NOT NULL,
    CONSTRAINT fk_category
        FOREIGN KEY (category_id)
        REFERENCES categories(category_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_stock_size ON stocks (stock_size);
CREATE INDEX IF NOT EXISTS idx_stock_sell_price ON stocks (stock_sell_price);
CREATE INDEX IF NOT EXISTS idx_stock_purchace_price ON stocks (stock_purchace_price);
CREATE INDEX IF NOT EXISTS idx_stock_amount ON stocks (stock_amount);
CREATE INDEX IF NOT EXISTS idx_stock_unit ON stocks (stock_unit);

SELECT * FROM information_schema.tables WHERE table_name = 'stocks';




/*
    create sales table
    design ref: penjualan
*/
CREATE TABLE IF NOT EXISTS sales (
    sales_id      VARCHAR(16) NOT NULL PRIMARY KEY,
    stock_id      VARCHAR(16) NOT NULL,
    customer_id   VARCHAR(16) NOT NULL,
    sales_date    DATE NOT NULL,
    sales_amount  INT NOT NULL,
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
);

CREATE INDEX IF NOT EXISTS idx_sales_date ON sales (sales_date);
CREATE INDEX IF NOT EXISTS idx_sales_amount ON sales (sales_amount);

SELECT * FROM information_schema.tables WHERE table_name = 'sales';

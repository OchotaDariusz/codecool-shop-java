DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;


CREATE TABLE products (
                          id          INTEGER     PRIMARY KEY     NOT NULL,
                          name        VARCHAR(200)                NOT NULL,
                          price       BIGINT                      NOT NULL,
                          currency    TEXT,
                          description  TEXT,
                          category_id INTEGER                     NOT NULL,
                          supplier_id INTEGER                     NOT NULL
);


CREATE TABLE product_categories (
                                    id          SERIAL      PRIMARY KEY     NOT NULL,
                                    name        VARCHAR(30)                 NOT NULL,
                                    description  TEXT,
                                    department  VARCHAR(30)
);


CREATE TABLE suppliers (
                           id          SERIAL      PRIMARY KEY     NOT NULL,
                           name        VARCHAR(30)                 NOT NULL,
                           description  TEXT
);


CREATE TABLE orders (
                        id          SERIAL      PRIMARY KEY     NOT NULL,
                        customer_id INTEGER                    NOT NULL,
                        status      TEXT                        NOT NULL,
                        amount      INTEGER                    NOT NULL
);


CREATE TABLE ordered_products (
                                  id          SERIAL      PRIMARY KEY     NOT NULL,
                                  product_id  INTEGER                     NOT NULL,
                                  order_id    INTEGER                     NOT NULL
);


CREATE TABLE users (
                       id           SERIAL PRIMARY KEY NOT NULL,
                       first_name   VARCHAR(30)                 NOT NULL,
                       last_name    VARCHAR(30)                 NOT NULL,
                       username     VARCHAR(30)                 NOT NULL,
                       email        VARCHAR(100)                NOT NULL,
                       address      VARCHAR(30)                 NOT NULL,
                       address2     VARCHAR(30),
                       country      VARCHAR(30)                 NOT NULL,
                       city         VARCHAR(30)                 NOT NULL,
                       zip          INTEGER
);



ALTER TABLE ONLY products
    ADD CONSTRAINT fk_products_category_id FOREIGN KEY (category_id) REFERENCES product_categories(id);

ALTER TABLE ONLY products
    ADD CONSTRAINT fk_products_supplier_id FOREIGN KEY (supplier_id) REFERENCES suppliers(id);

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_orders_customer_id FOREIGN KEY (customer_id) REFERENCES users(id);

ALTER TABLE ONLY ordered_products
    ADD CONSTRAINT fk_ordered_products_product_id FOREIGN KEY (product_id) REFERENCES products(id);

ALTER TABLE ONLY ordered_products
    ADD CONSTRAINT fk_ordered_products_order_id FOREIGN KEY (order_id) REFERENCES orders(id);





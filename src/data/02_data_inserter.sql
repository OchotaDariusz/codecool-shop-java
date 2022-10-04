-- suppliers
INSERT INTO public.suppliers (name, description) VALUES ('Amazon', 'Digital content and services');
INSERT INTO public.suppliers (name, description) VALUES ('Lenovo', 'Computers');
INSERT INTO public.suppliers (name, description) VALUES ('HP', 'Computers');
INSERT INTO public.suppliers (name, description) VALUES ('Samsung', 'Smart TV');
-- product category
INSERT INTO public.product_categories (name, department, description) VALUES ('Tablet', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO public.product_categories (name, department, description) VALUES ('Laptop', 'Hardware', 'A small, portable personal computer with a screen and alphanumeric keyboard.');
INSERT INTO public.product_categories (name, department, description) VALUES ('Smartphone', 'Hardware', 'A portable computer device that combines mobile telephone and computing functions into one unit.');
INSERT INTO public.product_categories (name, department, description) VALUES ('Smart TV', 'Hardware', 'A traditional television set with integrated Internet and interactive Web 2.0 features.');
-- products
INSERT INTO public.products (name, price, currency, description, category_id, supplier_id) VALUES ('Amazon Fire', 49.9, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 3, 1);
INSERT INTO public.products (name, price, currency, description, category_id, supplier_id) VALUES ('Lenovo IdealPad Mixx 700', 479, 'USD', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 2, 2);
INSERT INTO public.products (name, price, currency, description, category_id, supplier_id) VALUES ('Amazon Fire HD 8', 89, 'USD', 'Amazon latest Fire HD 8 tablet is a great value for media consumption.', 1, 1);
INSERT INTO public.products (name, price, currency, description, category_id, supplier_id) VALUES ('Crystal UHD 50TU7025', 499.9, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 4, 4);
INSERT INTO public.products (name, price, currency, description, category_id, supplier_id) VALUES ('Samsung QE65QN85B 2022', 1699, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 4, 4);
INSERT INTO public.products (name, price, currency, description, category_id, supplier_id) VALUES ('OMEN GeForce RTX™ 3070', 1399.9, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 2, 3);
INSERT INTO public.products (name, price, currency, description, category_id, supplier_id) VALUES ('ThinkBook 15 Gen 2', 999.9, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 2, 2);
INSERT INTO public.products (name, price, currency, description, category_id, supplier_id) VALUES ('ThinkPad X1 Carbon Gen 9', 1919, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 2, 2);
INSERT INTO public.products (name, price, currency, description, category_id, supplier_id) VALUES ('Lenovo Tab P12 Pro', 899.9, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 1, 2);
INSERT INTO public.products (name, price, currency, description, category_id, supplier_id) VALUES ('Galaxy Tab S7 FE Wifi', 499.9, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 1, 4);
INSERT INTO public.products (name, price, currency, description, category_id, supplier_id) VALUES ('Galaxy S22 Ultra', 1249.9, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 3, 4);
INSERT INTO public.products (name, price, currency, description, category_id, supplier_id) VALUES ('Galaxy S21 FE 5G', 809, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 3, 4);
INSERT INTO public.products (name, price, currency, description, category_id, supplier_id) VALUES ('Galaxy Z Flip4 I Bespoke Edition', 1169.9, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 3, 4);
package com.codecool.shop.config;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {

    public static OrderDao orderDataStore;
    public static ProductDao productDataStore;
    public static ProductCategoryDao productCategoryDataStore;
    public static SupplierDao supplierDataStore;
    public static UserDao userDataStore;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
            ShopDatabaseManager sdb = new ShopDatabaseManager();
        DataSource connection;
        try {
            connection = sdb.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        productDataStore = new ProductDaoJdbc(connection);
        productCategoryDataStore = new ProductCategoryDaoJdbc(connection);
        supplierDataStore = new SupplierDaoJdbc(connection);
        orderDataStore = new OrderDaoJdbc(connection);
        //productCategoryDataStore = new ProductCategoryDaoMem();
        //supplierDataStore = new SupplierDaoMem();
        //orderDataStore = new OrderDaoMem();
        userDataStore = new UserDaoMem();

        /*
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier hp = new Supplier("HP", "Computers");
        supplierDataStore.add(hp);
        Supplier samsung = new Supplier("Samsung", "Smart TV");
        supplierDataStore.add(samsung);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A small, portable personal computer with a screen and alphanumeric keyboard.");
        productCategoryDataStore.add(laptop);
        ProductCategory smartphone = new ProductCategory("Smartphone", "Hardware", "A portable computer device that combines mobile telephone and computing functions into one unit.");
        productCategoryDataStore.add(smartphone);
        ProductCategory tv = new ProductCategory("Smart TV", "Hardware", "A traditional television set with integrated Internet and interactive Web 2.0 features.");
        productCategoryDataStore.add(tv);


        //setting up products and printing it

        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        //new products
        productDataStore.add(new Product("Crystal UHD 50TU7025", new BigDecimal("499.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tv, samsung));
        productDataStore.add(new Product("Samsung QE65QN85B 2022", new BigDecimal("1699.0"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tv, samsung));
        productDataStore.add(new Product("OMEN GeForce RTX™ 3070", new BigDecimal("1399.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", laptop, hp));
        productDataStore.add(new Product("ThinkBook 15 Gen 2", new BigDecimal("999.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", laptop, lenovo));
        productDataStore.add(new Product("ThinkPad X1 Carbon Gen 9", new BigDecimal("1919.0"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", laptop, lenovo));
        productDataStore.add(new Product("Lenovo Tab P12 Pro", new BigDecimal("899.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, lenovo));
        productDataStore.add(new Product("Galaxy Tab S7 FE Wifi", new BigDecimal("499.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, samsung));
        productDataStore.add(new Product("Galaxy S22 Ultra", new BigDecimal("1249.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", smartphone, samsung));
        productDataStore.add(new Product("Galaxy S21 FE 5G", new BigDecimal("809.0"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", smartphone, samsung));
        productDataStore.add(new Product("Galaxy Z Flip4 I Bespoke Edition", new BigDecimal("1169.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", smartphone, samsung));
        */
        //setting up users
        userDataStore.add(new User("Tomek"));

//        //setting up test order
//        orderDataStore.add(new Order(1));
    }
}

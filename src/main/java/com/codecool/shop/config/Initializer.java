package com.codecool.shop.config;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@WebListener
public class Initializer implements ServletContextListener {

    public static OrderDao orderDataStore;
    public static ProductDao productDataStore;
    public static ProductCategoryDao productCategoryDataStore;
    public static SupplierDao supplierDataStore;
    public static UserDao userDataStore;
    public static Properties appProps;
    public static ProductInCartDao cartDataStore;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (getAppConfig().getProperty("dao").equals(AppDaoConfig.MEMORY.getConfigName())) {
            productDataStore = new ProductDaoMem();
            productCategoryDataStore = new ProductCategoryDaoMem();
            supplierDataStore = new SupplierDaoMem();
            orderDataStore = new OrderDaoMem();
            userDataStore = new UserDaoMem();
        } else {
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
            cartDataStore = new ProductInCartDaoJdbc(connection);
            userDataStore = new UserDaoJdbc(connection);
        }

    }

    private enum AppDaoConfig {
        MEMORY("memory"),
        JDBC("jdbc");

        private final String daoConfig;

        AppDaoConfig(String daoConfig) {
            this.daoConfig = daoConfig;
        }

        public String getConfigName() {
            return daoConfig;
        }
    }

    public static Properties getAppConfig() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "connection.properties";

        appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

//        String appDbUrl = appProps.getProperty("url");
//        String appDbName = appProps.getProperty("database");
//        String appDbUser = appProps.getProperty("user");
//        String appDbPass = appProps.getProperty("password");
        return appProps;
    }
}

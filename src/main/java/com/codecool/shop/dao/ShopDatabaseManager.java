package com.codecool.shop.dao;

import com.codecool.shop.config.Initializer;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

public class ShopDatabaseManager {

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        Properties appConfig = Initializer.getAppConfig();
        String dbName = appConfig.getProperty("database");
        String user = appConfig.getProperty("user");
        String password = appConfig.getProperty("password");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

    public DataSource getConnection() throws SQLException {
        DataSource dataSource;
        try {
            dataSource = connect();
        }
        catch(SQLException e){
            throw new SQLException("Problem connecting to PostreSQL database");
        }
        return dataSource;
    }
}

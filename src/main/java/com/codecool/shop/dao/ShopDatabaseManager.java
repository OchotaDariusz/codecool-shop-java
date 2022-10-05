package com.codecool.shop.dao;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ShopDatabaseManager {

    public void setup() throws SQLException {
        DataSource dataSource = connect();
//        playerDao = new PlayerDaoJdbc(dataSource);
//        gameStateDao = new GameStateDaoJdbc(dataSource);
//        itemDao = new ItemDaoJdbc(dataSource);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("DB_NAME");
        String user = System.getenv("USER");
        String password = System.getenv("PASSWORD");

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

package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private DataSource dataSource;


    @Override
    public void add(Supplier supplier){

    }

    @Override
    public Supplier find(int id){

        try (Connection conn = dataSource.getConnection()){
            String sql = """
                SELECT s.name,
                        s.description
                FROM suppliers AS s 
                WHERE s.id = ?;
                    """;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) return null;

            String name = rs.getString(1);
            String description = rs.getString(2);

            return new Supplier(name,description);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM suppliers WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeQuery();
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Supplier> getAll(){

        try (Connection conn = dataSource.getConnection()) {
            String sql = """
                SELECT  p.name,
                        p.description
                FROM suppliers AS p
                """;
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Supplier> supplierList = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString(1);
                String description = rs.getString(2);
                Supplier supplier = new Supplier(name, description);
                supplierList.add(supplier);
            }

            return supplierList;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}


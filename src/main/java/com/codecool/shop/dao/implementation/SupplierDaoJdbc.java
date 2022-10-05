package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(Supplier supplier){

        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO  suppliers (name, description) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next(); // Read next returned value - in ths case the first one. See ResultSet docs for more info
//            supplier.setId(resultSet.getInt(1));
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Supplier", throwables);
        }
    }

    @Override
    public Supplier find(int id){

        try (Connection conn = dataSource.getConnection()){
            String sql = """
                SELECT s.name,
                        s.description,
                        s.id
                FROM suppliers AS s 
                WHERE s.id = ?;
                    """;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            System.out.println((id));
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) return null;

            String name = rs.getString(1);
            String description = rs.getString(2);
            Supplier newSupplier = new Supplier(name,description);
            newSupplier.setId(rs.getInt(3));
            return newSupplier;
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
                SELECT  name,
                        description,
                        id
                FROM suppliers
                """;
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Supplier> supplierList = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString(1);
                String description = rs.getString(2);
                Supplier supplier = new Supplier(name, description);
                supplier.setId(rs.getInt(3));
                supplierList.add(supplier);
            }
            System.out.println(supplierList);
            return supplierList;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}


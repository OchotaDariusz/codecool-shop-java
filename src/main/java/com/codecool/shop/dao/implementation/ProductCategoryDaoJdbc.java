package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.awt.print.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {


    private DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO  product_categories (name, description, department) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getDepartment());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next(); // Read next returned value - in ths case the first one. See ResultSet docs for more info
            category.setId(resultSet.getInt(1));
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Author", throwables);
        }
    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, description, department FROM product_categories WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            String name = resultSet.getString(2);
            String desc = resultSet.getString(3);
            String dep = resultSet.getString(4);


            ProductCategory productCategory = new ProductCategory(name, desc, dep);


            productCategory.setId(id);
            return productCategory;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM product_categories WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Author", throwables);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            // FIRST STEP - read book_id, author_id and title
            String sql = "SELECT id, name, description, department FROM product_categories";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            List<ProductCategory> productCategories = new ArrayList<>();
            while (resultSet.next()) {
                // SECOND STEP - read all data from result set
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String desc = resultSet.getString(3);
                String dep = resultSet.getString(4);

                // THIRD STEP - find author with id == authorId
//                Author author = authorDao.get(authorId);

                // FOURTH STEP - create a new Book Class instance and add it to the result list
                ProductCategory productCategory = new ProductCategory(name, desc, dep);
                productCategory.setId(id);
                productCategories.add(productCategory);
            }
            return productCategories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

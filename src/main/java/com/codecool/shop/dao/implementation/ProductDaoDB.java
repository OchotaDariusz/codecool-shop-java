package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.List;

public class ProductDaoDB implements ProductDao {

    private DataSource dataSource;

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        String name = null;
        String description = null;
        BigDecimal defaultPrice = null;
        String defaultCurrency = null;
        ProductCategory productCategory = null;
        Supplier supplier = null;
        try (Connection conn = dataSource.getConnection()) {
            //create sql statement to retrieve all data necessary to create Product object
            String sql = """
            SELECT  p.name,\s
                            p.price,\s
                            p.currency,
                            p.description,
            				p_c.name,
                            p_c.department,
                            p_c.description,
            				s.name,
                            s.description \s
                    FROM products AS p
            		LEFT JOIN product_categories AS p_c
                    ON p.category_id = p_c.id
            		LEFT JOIN suppliers AS s
                    ON p.supplier_id = s.id
                    WHERE p.id = 1;
            """;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            name = rs.getString(1);
            description = rs.getString(2);
            defaultPrice = rs.getBigDecimal(3);
            defaultCurrency = rs.getString(4);
            productCategory = new ProductCategory(rs.getString(5), rs.getString(6), rs.getString(7));
            supplier = new Supplier(rs.getString(8), rs.getString(9));
            return new Product(name, defaultPrice, defaultCurrency, description, productCategory, supplier);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}

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
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private DataSource dataSource;

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
        System.out.println("ProductDaoJdbc created");
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            //create sql statement to retrieve all data necessary to create Product object
            String sql = """
            SELECT  p.name,
                            p.price,
                            p.currency,
                            p.description,
            				p_c.name,
                            p_c.department,
                            p_c.description,
            				s.name,
                            s.description 
                    FROM products AS p
            		LEFT JOIN product_categories AS p_c
                    ON p.category_id = p_c.id
            		LEFT JOIN suppliers AS s
                    ON p.supplier_id = s.id
                    WHERE p.id = ?;
            """;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            String name = rs.getString(1);
            String description = rs.getString(4);
            BigDecimal defaultPrice = rs.getBigDecimal(2);
            String defaultCurrency = rs.getString(3);
            ProductCategory productCategory = new ProductCategory(rs.getString(5), rs.getString(6), rs.getString(7));
            Supplier supplier = new Supplier(rs.getString(8), rs.getString(9));
            System.out.println("Found product in database");
            //System.out.println(new Product(name, defaultPrice, defaultCurrency, description, productCategory, supplier).toString());
            Product product = new Product(name, defaultPrice, defaultCurrency, description, productCategory, supplier);
            product.setId(id);
            System.out.println(product);
            return product;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading from CodecoolShop", e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            //create sql statement to retrieve all data necessary to create Product object
            String sql = "DELETE FROM products WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeQuery();
    } catch (SQLException e) {
            throw new RuntimeException("Error while reading from CodecoolShop", e);
        }
    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = """
            SELECT  p.name,
                            p.price,
                            p.currency,
                            p.description,
            				p_c.name,
                            p_c.department,
                            p_c.description,
            				s.name,
                            s.description,
                            p.id
                    FROM products AS p
            		LEFT JOIN product_categories AS p_c
                    ON p.category_id = p_c.id
            		LEFT JOIN suppliers AS s
                    ON p.supplier_id = s.id
            """;
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> allProducts = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                String name = rs.getString(1);
                String description = rs.getString(4);
                BigDecimal defaultPrice = rs.getBigDecimal(2);
                String defaultCurrency = rs.getString(3);
                ProductCategory productCategory = new ProductCategory(rs.getString(5), rs.getString(6), rs.getString(7));
                Supplier supplier = new Supplier(rs.getString(8), rs.getString(9));
                int id = rs.getInt(10);
                Product newProduct = new Product(name, defaultPrice, defaultCurrency, description, productCategory, supplier);
                newProduct.setId(id);
                allProducts.add(newProduct);
            }
            return allProducts;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading from CodecoolShop", e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            int id = supplier.getId();
            String sql = """
            SELECT  p.name,
                            p.price,
                            p.currency,
                            p.description,
            				p_c.name,
                            p_c.department,
                            p_c.description,
            				s.name,
                            s.description 
                    FROM products AS p
            		LEFT JOIN product_categories AS p_c
                    ON p.category_id = p_c.id
            		LEFT JOIN suppliers AS s
                    ON p.supplier_id = s.id
                    WHERE s.id=?
            """;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> allProducts = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                String name = rs.getString(1);
                String description = rs.getString(4);
                BigDecimal defaultPrice = rs.getBigDecimal(2);
                String defaultCurrency = rs.getString(3);
                ProductCategory productCategory = new ProductCategory(rs.getString(5), rs.getString(6), rs.getString(7));
                int productId = rs.getInt(10);
                Product newProduct = new Product(name, defaultPrice, defaultCurrency, description, productCategory, supplier);
                newProduct.setId(productId);
                allProducts.add(newProduct);
            }
            return allProducts;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading from CodecoolShop", e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            int id = productCategory.getId();
            String sql = """
            SELECT  p.name,
                            p.price,
                            p.currency,
                            p.description,
            				p_c.name,
                            p_c.department,
                            p_c.description,
            				s.name,
                            s.description,
                            p.id
                    FROM products AS p
            		LEFT JOIN product_categories AS p_c
                    ON p.category_id = p_c.id
            		LEFT JOIN suppliers AS s
                    ON p.supplier_id = s.id
                    WHERE s.id=?
            """;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> allProducts = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                String name = rs.getString(1);
                String description = rs.getString(4);
                BigDecimal defaultPrice = rs.getBigDecimal(2);
                String defaultCurrency = rs.getString(3);
                Supplier supplier = new Supplier(rs.getString(8), rs.getString(9));
                int productId = rs.getInt(10);
                Product newProduct = new Product(name, defaultPrice, defaultCurrency, description, productCategory, supplier);
                newProduct.setId(productId);
                allProducts.add(newProduct);
            }
            return allProducts;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading from CodecoolShop", e);
        }
    }
}

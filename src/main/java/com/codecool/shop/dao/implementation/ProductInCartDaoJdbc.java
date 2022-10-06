package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductInCartDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductInCartDaoJdbc implements ProductInCartDao {

    private DataSource dataSource;

    public ProductInCartDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addProduct(Product product, Order order) {
        int productId = product.getId();
        int orderId = order.getId();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cart (product_id, order_id) VALUES (?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, productId);
            st.setInt(2, orderId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading from CodecoolShop", e);
        }
    }

    @Override
    public void removeProduct(Product product, Order order) {
        int productId = product.getId();
        int orderId = order.getId();
        try (Connection conn = dataSource.getConnection()) {
            String sql = """
                            DELETE FROM cart
                            WHERE id IN (
                                SELECT id FROM
                                cart WHERE order_id=? AND product_id=? LIMIT 1
                                )
                           """;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderId);
            st.setInt(2, productId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading from CodecoolShop", e);
        }
    }

    @Override
    public void removeProductsOfGivenType(Product product, Order order) {
        int productId = product.getId();
        int orderId = order.getId();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM cart WHERE order_id = ? AND product_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderId);
            st.setInt(2, productId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading from CodecoolShop", e);
        }
    }

    @Override
    public void emptyCart(Order order) {
        int orderId = order.getId();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM cart";
            PreparedStatement st = conn.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading from CodecoolShop", e);
        }
    }

    @Override
    public List<Integer> getProductIdsFromCart(Order order) {
        List<Integer> productIds = new ArrayList<>();
        int orderId = order.getId();
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM cart WHERE order_id=";
            sql+=orderId;
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                int product_id = rs.getInt(2);
                productIds.add(product_id);
            }
            return productIds;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading from getProductsFromCart", e);
        }
    }
}

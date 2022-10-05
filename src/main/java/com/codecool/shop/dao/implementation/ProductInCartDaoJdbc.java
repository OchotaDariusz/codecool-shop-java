package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductInCartDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            st.executeQuery();
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
                                cart WHERE order_id=2 AND product_id=1 LIMIT 1
                                )
                           """;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderId);
            st.setInt(2, productId);
            st.executeQuery();
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
            st.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading from CodecoolShop", e);
        }
    }

}

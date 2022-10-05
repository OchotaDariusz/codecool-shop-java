package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;

public class OrderDaoJdbc implements OrderDao {

    private DataSource dataSource;

    public OrderDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Order createNewOrder(int userId) {
        Order order = new Order(userId);
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO  orders (customer_id, status, amount) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getId());
            statement.setString(2, order.getOrderStatus().getName());
            statement.setString(3, order.getAmount().toString());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next(); // Read next returned value - in ths case the first one. See ResultSet docs for more info
            order.setId(resultSet.getInt(1));
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Author", throwables);
        }
        return order;
    }

    @Override
    public Order findByUserId(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT customer_id, status, amount FROM orders WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            int customerId = resultSet.getInt(2);
            Order.OrderStatus status = Order.OrderStatus.valueOf(resultSet.getString(3));
            BigDecimal amount = BigDecimal.valueOf(Long.parseLong(resultSet.getString(4)));

            Order order = new Order(customerId);
            order.setOrderStatus(status);
            order.setAmount(amount);
            order.setId(userId);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
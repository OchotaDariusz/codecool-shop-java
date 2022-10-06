package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Arrays;

public class OrderDaoJdbc implements OrderDao {

    private DataSource dataSource;

    public OrderDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Order createNewOrder(int userId) {
        Order order = new Order(userId);
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO  orders (customer_id, status, amount) VALUES (?, ?, 0)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userId);
            statement.setString(2, order.getOrderStatus().getName());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            order.setId(resultSet.getInt(1));
            return order;
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Order", throwables);
        }
    }

    @Override
    public void finaliseOrder(Order order) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = """
                    UPDATE orders
                    SET  status = ?,
                        amount = ?,
                        first_name = ?,
                        last_name = ?,
                        email = ?,
                        address = ?,
                        address2 = ?,
                        country = ?,
                        city = ?,
                        zip = ?
                    WHERE id = ?
                    """;
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, order.getOrderStatus().getName());
            statement.setBigDecimal(2, order.getAmount());
            statement.setString(3, order.getFirstName());
            statement.setString(4, order.getLastName());
            statement.setString(5, order.getEmail());
            statement.setString(6, order.getAddress());
            statement.setString(7, order.getAddress2());
            statement.setString(8, order.getCountry());
            statement.setString(9, order.getCity());
            statement.setString(10, order.getZip());
            statement.setInt(11, order.getId());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while finalising Order", throwables);
        }
    }

    @Override
    public Order findByUserId(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT customer_id, status, amount, first_name, last_name, email, address, address2, country, city, zip, id FROM orders WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            int customerId = resultSet.getInt(1);
            String statusName = resultSet.getString(2);
            Order.OrderStatus status = Arrays.stream(Order.OrderStatus.values()).filter(orderStatus -> orderStatus.getName().equals(statusName)).findFirst().get();
            BigDecimal amount = resultSet.getBigDecimal(3);

            Order order = new Order(customerId);
            order.setOrderStatus(status);
            order.setAmount(amount);
            order.setId(userId);
            order.setFirstName(resultSet.getString(4));
            order.setLastName(resultSet.getString(5));
            order.setEmail(resultSet.getString(6));
            order.setAddress(resultSet.getString(7));
            order.setAddress2(resultSet.getString(8));
            order.setCountry(resultSet.getString(9));
            order.setCity(resultSet.getString(10));
            order.setZip(resultSet.getString(11));
            order.setId(resultSet.getInt(12));
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
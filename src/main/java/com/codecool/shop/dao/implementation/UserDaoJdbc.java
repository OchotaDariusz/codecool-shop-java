package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoJdbc implements UserDao {

    private DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {

        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO  users (username, password) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next(); // Read next returned value - in ths case the first one. See ResultSet docs for more info
//            user.setId(resultSet.getInt(1));
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new User", throwables);
        }
    }

    @Override
    public User find(int id) {

        try (Connection conn = dataSource.getConnection()) {
            String sql = """
                    SELECT username, password
                            
                    FROM users
                    WHERE id = ?;
                        """;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();


            return getUserFromQueryResult(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(String username) {

        try (Connection conn = dataSource.getConnection()) {
            String sql = """
                    SELECT username, password
                            
                    FROM users
                    WHERE username = ?;
                        """;
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();


            return getUserFromQueryResult(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static User getUserFromQueryResult(ResultSet rs) throws SQLException {
        rs.next();
        String username = rs.getString(1);
        String password = rs.getString(2);

        User user = new User(username, password);
        user.setPassword(password);
        return user;
    }
}

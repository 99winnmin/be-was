package domain.user.infrastructure;

import common.db.DatabaseConfig;
import common.logger.CustomLogger;
import domain.user.command.domain.User;
import domain.user.command.domain.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final DataSource dataSource = DatabaseConfig.getDatasource();

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO bewas_users (user_id, password, username, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }
    }

    @Override
    public Optional<User> findUserById(String userId) {
        String sql = "SELECT * FROM bewas_users WHERE user_id = ?";
        User user = null;

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                        rs.getString("user_id"),
                        rs.getString("password"),
                        rs.getString("username"),
                        rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Collection<User> findAll() {
        String sql = "SELECT * FROM bewas_users";
        Collection<User> users = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(
                    rs.getString("user_id"),
                    rs.getString("password"),
                    rs.getString("username"),
                    rs.getString("email")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }

        return users;
    }

    @Override
    public void deleteUser(String userId) {
        String sql = "DELETE FROM bewas_users WHERE user_id = ?";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }
    }
}

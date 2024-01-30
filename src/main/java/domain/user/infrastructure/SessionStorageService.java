package domain.user.infrastructure;

import common.db.DatabaseConfig;
import common.logger.CustomLogger;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionStorageService {

    private final DataSource dataSource = DatabaseConfig.getDatasource();

    public void saveSession(String sessionId, String userId) {
        String sql = "INSERT INTO session_storage (session_id, session_value) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sessionId);
            pstmt.setString(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }
    }

    public Optional<String> getSessionIdByUserId(String userId) {
        String sql = "SELECT session_id FROM session_storage WHERE session_value = ?";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rs.getString("session_id"));
                }
            }
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }

        return Optional.empty();
    }

    public void removeSession(String sessionId) {
        String sql = "DELETE FROM session_storage WHERE session_id = ?";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sessionId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }
    }

    public Optional<String> getUserIdBySessionId(String sessionId) {
        String sql = "SELECT session_value FROM session_storage WHERE session_id = ?";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sessionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.ofNullable(rs.getString("session_value"));
                }
            }
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }

        return Optional.empty();
    }

    public List<String> getAllUserId() {
        String sql = "SELECT session_value FROM session_storage";
        List<String> userIds = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                userIds.add(rs.getString("session_value"));
            }
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }

        return userIds;
    }
}

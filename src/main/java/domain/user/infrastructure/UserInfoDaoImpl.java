package domain.user.infrastructure;

import common.db.DatabaseConfig;
import common.logger.CustomLogger;
import domain.user.query.dao.UserInfoDao;
import domain.user.query.dto.UserInfo;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserInfoDaoImpl implements UserInfoDao {

    private final DataSource dataSource = DatabaseConfig.getDatasource();

    @Override
    public void addUserInfo(UserInfo userInfo) {
        String sql = "INSERT INTO bewas_user_infos (userinfo_id, username, email) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userInfo.getUserId());
            pstmt.setString(2, userInfo.getUserName());
            pstmt.setString(3, userInfo.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }
    }

    @Override
    public Optional<UserInfo> findUserInfoById(String userId) {
        String sql = "SELECT * FROM bewas_user_infos WHERE userinfo_id = ?";
        UserInfo userInfo = null;

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userInfo = new UserInfo(
                        rs.getString("userinfo_id"),
                        rs.getString("username"),
                        rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }

        return Optional.ofNullable(userInfo);
    }

    @Override
    public List<UserInfo> findAllUserInfo() {
        String sql = "SELECT * FROM bewas_user_infos";
        List<UserInfo> userInfos = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                UserInfo userInfo = new UserInfo(
                    rs.getString("userinfo_id"),
                    rs.getString("username"),
                    rs.getString("email"));
                userInfos.add(userInfo);
            }
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }

        return userInfos;
    }
}

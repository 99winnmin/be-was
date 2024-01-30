package domain.qna.infrastructure;

import common.db.DatabaseConfig;
import common.logger.CustomLogger;
import domain.qna.query.dao.QuestionDao;
import domain.qna.query.dto.QuestionDetailInfo;
import domain.qna.query.dto.QuestionSimpleInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class QuestionDaoImpl implements QuestionDao {
    private final DataSource dataSource = DatabaseConfig.getDatasource();

    @Override
    public void addQuestionSimpleInfo(QuestionSimpleInfo questionSimpleInfo) {
        String sql = "INSERT INTO bewas_question_simple_info (question_simple_info_id, question_id, title, created_at, writer_name) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, questionSimpleInfo.getQuestionSimpleInfoId());
            pstmt.setString(2, questionSimpleInfo.getQuestionId());
            pstmt.setString(3, questionSimpleInfo.getTitle());
            pstmt.setString(4, questionSimpleInfo.getCreatedAt());
            pstmt.setString(5, questionSimpleInfo.getWriterId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }
    }

    @Override
    public void addQuestionDetailInfo(QuestionDetailInfo questionDetailInfo) {
        String sql = "INSERT INTO bewas_question_detail_info (question_detail_info_id, question_id, title, contents, created_at, writer_id, writer_name) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, questionDetailInfo.getQuestionDetailInfoId());
            pstmt.setString(2, questionDetailInfo.getQuestionId());
            pstmt.setString(3, questionDetailInfo.getTitle());
            pstmt.setString(4, questionDetailInfo.getContents());
            pstmt.setString(5, questionDetailInfo.getCreatedAt());
            pstmt.setString(6, questionDetailInfo.getWriterId());
            pstmt.setString(7, questionDetailInfo.getWriterName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }
    }

    @Override
    public Optional<QuestionSimpleInfo> findQuestionSimpleInfoByQuestionId(String questionId) {
        String sql = "SELECT * FROM bewas_question_simple_info WHERE question_id = ?";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, questionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new QuestionSimpleInfo(
                        rs.getString("question_simple_info_id"),
                        rs.getString("question_id"),
                        rs.getString("title"),
                        rs.getString("created_at"),
                        rs.getString("writer_name")
                    ));
                }
            }
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<QuestionDetailInfo> findQuestionDetailInfoByQuestionId(String questionId) {
        String sql = "SELECT * FROM bewas_question_detail_info WHERE question_id = ?";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, questionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new QuestionDetailInfo(
                        rs.getString("question_detail_info_id"),
                        rs.getString("question_id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("created_at"),
                        rs.getString("writer_id"),
                        rs.getString("writer_name")
                    ));
                }
            }
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }

        return Optional.empty();
    }


    @Override
    public List<QuestionSimpleInfo> findAllQuestionSimpleInfo() {
        String sql = "SELECT * FROM bewas_question_simple_info";
        List<QuestionSimpleInfo> questionSimpleInfos = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                questionSimpleInfos.add(new QuestionSimpleInfo(
                    rs.getString("question_simple_info_id"),
                    rs.getString("question_id"),
                    rs.getString("title"),
                    rs.getString("created_at"),
                    rs.getString("writer_name")
                ));
            }
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }
        return questionSimpleInfos;
    }
}

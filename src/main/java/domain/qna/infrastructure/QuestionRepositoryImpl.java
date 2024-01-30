package domain.qna.infrastructure;

import common.db.DatabaseConfig;
import common.logger.CustomLogger;
import domain.qna.command.domain.Question;
import domain.qna.command.domain.QuestionRepository;
import domain.user.command.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import javax.sql.DataSource;

public class QuestionRepositoryImpl implements QuestionRepository {

    private final DataSource dataSource = DatabaseConfig.getDatasource();

    @Override
    public void addQuestion(Question question) {
        String sql = "INSERT INTO bewas_question (question_id, title, contents, created_at, writer_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, question.getQuestionId());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setString(4, question.getCreatedAt());
            pstmt.setString(5, question.getWriterId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }

    }

    @Override
    public Optional<Question> findQuestionById(String questionId) {
        String sql = "SELECT * FROM bewas_question WHERE user_id = ?";
        Question question = null;

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, questionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    question = new Question(
                        rs.getString("question_id"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("created_at"),
                        rs.getString("writer_id")
                    );
                }
            }
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }

        return Optional.ofNullable(question);
    }

    @Override
    public Collection<Question> findAll() {
        String sql = "SELECT * FROM bewas_question";
        Collection<Question> questions = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Question question = new Question(
                    rs.getString("question_id"),
                    rs.getString("title"),
                    rs.getString("contents"),
                    rs.getString("created_at"),
                    rs.getString("writer_id")
                );
                questions.add(question);
            }
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }

        return questions;
    }

    @Override
    public void deleteQuestion(String questionId) {
        String sql = "DELETE FROM bewas_question WHERE question_id = ?";

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, questionId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            CustomLogger.printError(e);
        }
    }
}

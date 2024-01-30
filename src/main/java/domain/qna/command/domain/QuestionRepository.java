package domain.qna.command.domain;

import java.util.Collection;
import java.util.Optional;

public interface QuestionRepository {
    public void addQuestion(Question question);

    public Optional<Question> findQuestionById(String questionId);

    public Collection<Question> findAll();

    public void deleteQuestion(String questionId);

}

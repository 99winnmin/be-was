package domain.qna.command.application;

import common.http.response.HttpStatusCode;
import common.utils.ResponseUtils;
import domain.qna.command.domain.Question;
import domain.qna.command.domain.QuestionRepository;
import domain.qna.infrastructure.QuestionCommandHandler;
import domain.qna.infrastructure.QuestionRepositoryImpl;
import domain.user.command.domain.UserRepository;
import domain.user.infrastructure.SessionStorageService;
import domain.user.infrastructure.UserRepositoryImpl;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import webserver.container.CustomThreadLocal;

public class QuestionCreateService {
    private QuestionRepository questionRepository;
    private SessionStorageService sessionStorageService;
    private QuestionCommandHandler questionCommandHandler;

    public QuestionCreateService() {
        this.questionRepository = new QuestionRepositoryImpl();
        this.sessionStorageService = new SessionStorageService();
        this.questionCommandHandler = new QuestionCommandHandler();
    }

    public void makeNewQuestion(QuestionCreateRequest questionCreateRequest, String sessionId) {
        Optional<String> optionalUserId = sessionStorageService.getUserIdBySessionId(sessionId);
        if (optionalUserId.isEmpty()) {
            CustomThreadLocal.onFailure(HttpStatusCode.FOUND, ResponseUtils.makeRedirection("/user/login.html"), new byte[0]);
            return;
        }

        Question newQuestion = createQuestionEntity(questionCreateRequest, optionalUserId.get());
        saveQuestion(newQuestion);
        CustomThreadLocal.onSuccess(HttpStatusCode.FOUND, ResponseUtils.makeRedirection("/index.html"), new byte[0]);

        questionCommandHandler.addSaveQuestionInfo(newQuestion);
    }

    private Question createQuestionEntity(QuestionCreateRequest questionCreateRequest, String userId) {
        return new Question(
            UUID.randomUUID().toString(),
            questionCreateRequest.getTitle(),
            questionCreateRequest.getContents(),
            LocalDateTime.now().toString(),
            userId
        );
    }

    private void saveQuestion(Question question) {
        questionRepository.addQuestion(question);
    }
}

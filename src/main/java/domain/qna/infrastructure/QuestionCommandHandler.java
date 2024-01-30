package domain.qna.infrastructure;

import common.logger.CustomLogger;
import common.utils.AsyncExecutor;
import domain.qna.command.domain.Question;
import domain.qna.query.dto.QuestionDetailInfo;
import domain.qna.query.dto.QuestionSimpleInfo;
import domain.user.infrastructure.UserInfoDaoImpl;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class QuestionCommandHandler {

    private QuestionDaoImpl questionDao;
    private UserInfoDaoImpl userInfoDao;

    public QuestionCommandHandler() {
        questionDao = new QuestionDaoImpl();
        userInfoDao = new UserInfoDaoImpl();
    }

    public void addSaveQuestionInfo(Question question) {
        AsyncExecutor.getInstance().addEvent(() -> {
            CompletableFuture<Void> simpleInfoFuture = saveSimpleInfoAsync(question);
            CompletableFuture<Void> detailInfoFuture = saveDetailInfoAsync(question);

            CompletableFuture
                .allOf(simpleInfoFuture, detailInfoFuture)
                .thenAccept(aVoid -> handleResults(question.getQuestionId()));
        });
    }

    private CompletableFuture<Void> saveSimpleInfoAsync(Question question) {
        return CompletableFuture.runAsync(() -> {
            questionDao.addQuestionSimpleInfo(
                new QuestionSimpleInfo(
                    UUID.randomUUID().toString(),
                    question.getQuestionId(),
                    question.getTitle(),
                    question.getCreatedAt(),
                    question.getWriterId())
            );
        });
    }

    private CompletableFuture<Void> saveDetailInfoAsync(Question question) {
        String writerName = userInfoDao.findUserInfoById(question.getWriterId()).get().getUserName();
        return CompletableFuture.runAsync(() -> {
            questionDao.addQuestionDetailInfo(
                new QuestionDetailInfo(
                    UUID.randomUUID().toString(),
                    question.getQuestionId(),
                    question.getTitle(),
                    question.getContents(),
                    question.getCreatedAt(),
                    question.getWriterId(),
                    writerName)
            );
        });
    }

    private void handleResults(String questionId)
    {
        if (questionDao.findQuestionSimpleInfoByQuestionId(questionId).isEmpty() || questionDao.findQuestionDetailInfoByQuestionId(questionId).isEmpty()) {
            CustomLogger.printError(new RuntimeException("QuestionInfo is not saved"));
        }
    }

}

package domain.qna.query.application;

import common.http.response.HttpStatusCode;
import common.utils.ResponseUtils;
import domain.qna.infrastructure.QuestionDaoImpl;
import domain.qna.query.dao.QuestionDao;
import domain.qna.query.dto.QuestionDetailInfo;
import domain.qna.query.dto.QuestionSimpleInfo;
import domain.user.infrastructure.SessionStorageService;
import java.util.List;
import java.util.Optional;
import webserver.container.CustomThreadLocal;

public class QuestionSearchService {

    private QuestionDao questionDao;
    private SessionStorageService sessionStorageService;

    public QuestionSearchService() {
        questionDao = new QuestionDaoImpl();
        sessionStorageService = new SessionStorageService();
    }

    public void getSimpleQuestionInfoList() {
        List<QuestionSimpleInfo> questionSimpleInfoList = questionDao.findAllQuestionSimpleInfo();

        CustomThreadLocal.onSuccess(
            HttpStatusCode.OK,
            ResponseUtils.makeJsonResponseHeader(),
            QuestionResponseToJsonConverter.questionSimpleInfoResponseConvertToJson(questionSimpleInfoList).getBytes());
    }

    public void getQuestionDetailInfo(String questionId) {
        Optional<QuestionDetailInfo> questionDetailInfo = questionDao.findQuestionDetailInfoByQuestionId(questionId);
        if (questionDetailInfo.isEmpty()) {
            CustomThreadLocal.onFailure(HttpStatusCode.NOT_FOUND, ResponseUtils.makeRedirection("/index.html"), new byte[0]);
            return;
        }

        CustomThreadLocal.onSuccess(
            HttpStatusCode.OK,
            ResponseUtils.makeJsonResponseHeader(),
            QuestionResponseToJsonConverter.questionDetailInfoResponseConvertToJson(questionDetailInfo.get()).getBytes());
    }
}

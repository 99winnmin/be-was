package domain.qna.presentation;

import common.annotation.RequestBody;
import common.annotation.RequestMapping;
import common.annotation.RequestParam;
import common.http.request.HttpMethod;
import common.http.request.HttpRequest;
import common.http.response.HttpStatusCode;
import common.utils.CookieParser;
import common.utils.ResponseUtils;
import domain.qna.command.application.QuestionCreateRequest;
import domain.qna.command.application.QuestionCreateService;
import domain.qna.query.application.QuestionSearchService;
import java.util.Optional;
import webserver.container.CustomThreadLocal;

public class QnAController {
    private QuestionCreateService questionCreateService;
    private QuestionSearchService questionSearchService;

    public QnAController() {
        questionCreateService = new QuestionCreateService();
        questionSearchService = new QuestionSearchService();
    }

    @RequestMapping(path = "/qna/list", method = HttpMethod.GET)
    public void getQnAList() {
        questionSearchService.getSimpleQuestionInfoList();
    }

    @RequestMapping(path = "/qna/detail", method = HttpMethod.GET)
    public void getQnADetail(@RequestParam(name = "questionId") String questionId) {
        questionSearchService.getQuestionDetailInfo(questionId);
    }

    @RequestMapping(method = HttpMethod.POST, path = "/qna/save")
    public void saveQnA(@RequestBody QuestionCreateRequest questionCreateRequest)
    {
        HttpRequest httpRequest = CustomThreadLocal.getHttpRequest();
        Optional<String> sessionId = CookieParser.parseSidFromHeader(httpRequest);
        if (sessionId.isEmpty()) {
            CustomThreadLocal.onFailure(HttpStatusCode.FOUND, ResponseUtils.makeRedirection("/user/login.html"), new byte[0]);
            return;
        }

        if (QuARequestValidator.qnaCreateRequestValidate(questionCreateRequest)) return;

        questionCreateService.makeNewQuestion(questionCreateRequest, sessionId.get());
    }

}

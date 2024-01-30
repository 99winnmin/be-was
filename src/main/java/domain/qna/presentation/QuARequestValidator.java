package domain.qna.presentation;

import common.http.response.HttpStatusCode;
import domain.qna.command.application.QuestionCreateRequest;
import java.util.HashMap;
import webserver.container.CustomThreadLocal;

public class QuARequestValidator {
    public static boolean qnaCreateRequestValidate(QuestionCreateRequest questionCreateRequest) {
        if (questionCreateRequest.getTitle() == null ||
            questionCreateRequest.getContents() == null) {
            CustomThreadLocal.onFailure(HttpStatusCode.BAD_REQUEST, new HashMap<>(), "title or contents is null".getBytes());
            return true;
        }
        return false;
    }

}

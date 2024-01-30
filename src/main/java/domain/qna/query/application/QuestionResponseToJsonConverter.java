package domain.qna.query.application;

import common.utils.JsonConverter;
import domain.qna.query.dto.QuestionDetailInfo;
import domain.qna.query.dto.QuestionSimpleInfo;
import java.util.List;
import java.util.Map;

public class QuestionResponseToJsonConverter {
    public static String questionSimpleInfoResponseConvertToJson(List<QuestionSimpleInfo> questionSimpleInfoList) {
        return JsonConverter.mapToListObjectJson2(questionSimpleInfoList);
    }

    public static String questionDetailInfoResponseConvertToJson(QuestionDetailInfo questionDetailInfo) {
        Map<String, String> map = Map.of(
            "questionDetailInfoId", questionDetailInfo.getQuestionDetailInfoId(),
            "questionId", questionDetailInfo.getQuestionId(),
            "title", questionDetailInfo.getTitle(),
            "contents", questionDetailInfo.getContents(),
            "createdAt", questionDetailInfo.getCreatedAt(),
            "writerId", questionDetailInfo.getWriterId(),
            "writerName", questionDetailInfo.getWriterName()
        );
        return JsonConverter.mapToSingleObjectJson(map);
    }
}

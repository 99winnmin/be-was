package common.utils;

import domain.qna.query.dto.QuestionSimpleInfo;
import domain.user.query.application.UserInfoResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonConverter {
    public static String mapToSingleObjectJson(Map<String, String> map) {
        return map.entrySet().stream()
            .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
            .collect(Collectors.joining(",", "{", "}"));
    }

    public static String mapToListObjectJson(List<UserInfoResponse> userInfoResponseList) {
        StringBuilder jsonBuilder = new StringBuilder();

        jsonBuilder.append("[\n  {");

        // 맵의 항목을 반복하여 JSON 문자열을 구성합니다.
        int i = 0;
        for (UserInfoResponse userInfoResponse : userInfoResponseList) {
            if (i > 0) jsonBuilder.append(",\n  {");

            jsonBuilder.append("\n    \"userId\": \"").append(userInfoResponse.getUserId()).append("\",");
            jsonBuilder.append("\n    \"userName\": \"").append(userInfoResponse.getUserName()).append("\",");
            jsonBuilder.append("\n    \"email\": \"").append(userInfoResponse.getEmail()).append("\"");

            jsonBuilder.append("\n  }");
            i++;
        }

        jsonBuilder.append("\n]");

        return jsonBuilder.toString();
    }

    public static String mapToListObjectJson2(List<QuestionSimpleInfo> questionSimpleInfoList) {
        StringBuilder jsonBuilder = new StringBuilder();

        jsonBuilder.append("[\n");

        // 맵의 항목을 반복하여 JSON 문자열을 구성합니다.
        int i = 0;
        for (QuestionSimpleInfo questionSimpleInfo : questionSimpleInfoList) {
            if (i == 0) jsonBuilder.append("  {" + "\n");
            else if (i > 0) jsonBuilder.append(",\n  {");

            jsonBuilder.append("\n    \"questionSimpleInfoId\": \"").append(questionSimpleInfo.getQuestionSimpleInfoId()).append("\",");
            jsonBuilder.append("\n    \"questionId\": \"").append(questionSimpleInfo.getQuestionId()).append("\",");
            jsonBuilder.append("\n    \"title\": \"").append(questionSimpleInfo.getTitle()).append("\",");
            jsonBuilder.append("\n    \"createdAt\": \"").append(questionSimpleInfo.getCreatedAt()).append("\",");
            jsonBuilder.append("\n    \"writerId\": \"").append(questionSimpleInfo.getWriterId()).append("\"");

            jsonBuilder.append("\n  }");
            i++;
        }

        jsonBuilder.append("\n]");

        return jsonBuilder.toString();
    }

}

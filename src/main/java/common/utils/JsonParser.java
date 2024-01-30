package common.utils;

import java.util.HashMap;
import java.util.Map;

public class JsonParser {
    public static Map<String, String> parseRequestBody(String requestBody) {
        Map<String, String> parsedBody = new HashMap<>();

        // JSON 형식의 괄호 제거
        String json = requestBody.trim().replaceAll("^\\{", "").replaceAll("\\}$", "");

        // 쉼표로 구분하여 key-value 쌍 분리
        String[] pairs = json.split(",");
        for (String pair : pairs) {
            // 콜론으로 key와 value 분리
            String[] keyValue = pair.split(":", 2);

            if (keyValue.length == 2) {
                // 따옴표 제거
                String key = keyValue[0].trim().replaceAll("^\"|\"$", "");
                String value = keyValue[1].trim().replaceAll("^\"|\"$", "");
                parsedBody.put(key, value);
            }
        }

        return parsedBody;
    }
}

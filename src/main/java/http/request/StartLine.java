package http.request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StartLine {

    private HttpMethod httpMethod;
    private String requestTarget;
    private String httpVersion;

    public StartLine(String httpMethod, String requestTarget, String httpVersion) {
        this.httpMethod = HttpMethod.parseHttpMethod(httpMethod);
        this.requestTarget = requestTarget;
        this.httpVersion = httpVersion;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public String parsingPath() {
        String[] url = requestTarget.split("\\?");
        return url[0];
    }

    public Map<String, String> parsingParams() {
        if (!requestTarget.contains("?")) {
            return new HashMap<>();
        }
        String params = requestTarget.split("\\?")[1];

        Map<String, String> paramsMap = new HashMap<>();
        Arrays.stream(params.split("&"))
            .map(param -> param.split("="))
            .forEach(param -> paramsMap.put(param[0], param[1]));
        return paramsMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb
            .append("httpMethod : ").append(httpMethod).append("\n")
            .append("requestTarget : ").append(requestTarget).append("\n")
            .append("httpVersion : ").append(httpVersion).toString();
    }
}

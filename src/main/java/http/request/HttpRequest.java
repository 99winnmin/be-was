package http.request;

import java.util.Map;

public class HttpRequest {

    private StartLine startLine;
    private Header header;
    private Body body;

    public HttpRequest(StartLine startLine,
        Header header, Body body) {
        this.startLine = startLine;
        this.header = header;
        this.body = body;
    }

    public StartLine getHttpRequestStartLine() {
        return startLine;
    }

    public Header getHttpRequestHeader() {
        return header;
    }

    public Body getHttpRequestBody() {
        return body;
    }

    public String getRequestPath() {
        return startLine.parsingPath();
    }

    public Map<String, String> getRequestParams() {
        return startLine.parsingParams();
    }
}

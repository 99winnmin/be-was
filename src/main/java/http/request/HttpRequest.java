package http.request;

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
}

package http.response;

import java.nio.ByteBuffer;

public class HttpResponse {

    private HttpResponseStartLine startLine;
    private HttpResponseHeader header;
    private byte[] body;

    public HttpResponse(HttpResponseStartLine startLine, HttpResponseHeader header, byte[] body) {
        this.startLine = startLine;
        this.header = header;
        this.body = body;
    }

    public HttpResponseStartLine getStartLine() {
        return startLine;
    }

    public HttpResponseHeader getHeader() {
        return header;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public byte[] convertResponseToByteArray() {
        byte[] startLine = this.startLine.toString().getBytes();
        byte[] header = this.header.toString().getBytes();
        byte[] body = this.body;

        int totalLength = startLine.length + header.length + body.length;

        ByteBuffer buffer = ByteBuffer.allocate(totalLength);
        buffer.put(startLine);
        buffer.put(header);
        buffer.put(body);

        return buffer.array();
    }

}

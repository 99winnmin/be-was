package http.request;

import java.util.HashMap;
import java.util.Map;

public class Header {

    private final Map<String, String> headers;

    public Header(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        headers.forEach((key, value) -> sb.append(key).append(" : ").append(value).append("\n"));
        return sb.toString();
    }
}

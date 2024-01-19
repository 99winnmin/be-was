package http.request;

import java.util.Map;

public class Body {

    private final Map<String, String> body;

    public Body(Map<String, String> body) {
        this.body = body;
    }

    public Map<String, String> getBody() {
        return body;
    }

}

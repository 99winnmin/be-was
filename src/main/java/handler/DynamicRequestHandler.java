package handler;


import http.request.HttpRequest;
import java.util.Map;
import service.Service;
import service.user.UserServiceFactory;

public class DynamicRequestHandler {

    private DynamicRequestHandler() {
    }

    private static class SingletonHelper {

        private static final DynamicRequestHandler SINGLETON = new DynamicRequestHandler();
    }

    public static DynamicRequestHandler getInstance() {
        return SingletonHelper.SINGLETON;
    }

    public byte[] process(HttpRequest httpRequest) {
        String path = httpRequest.getRequestPath();
        Map<String, String> params = httpRequest.getRequestParams();

        if (path.startsWith("/user")) {
            UserServiceFactory userServiceFactory = UserServiceFactory.getInstance();
            Service userService = userServiceFactory.createService(path);
            return userService.execute(
                httpRequest.getHttpRequestStartLine().getHttpMethod(),
                params,
                httpRequest.getHttpRequestBody().getBody()
            );
        } // 또 다른 도메인 서비스가 추가되면 여기에 추가
        else {
            throw new IllegalArgumentException("Unsupported URL: " + path);
        }
    }
}

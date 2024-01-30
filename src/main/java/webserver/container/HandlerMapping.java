package webserver.container;


import common.http.request.HttpMethod;
import common.http.request.HttpRequest;
import common.http.response.HttpStatusCode;
import common.utils.ResponseUtils;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

public class HandlerMapping {

    private HandlerMapping() {
    }

    private static class SingletonHelper {

        private static final HandlerMapping SINGLETON = new HandlerMapping();
    }

    public static HandlerMapping getInstance() {
        return SingletonHelper.SINGLETON;
    }

    public void process(HttpRequest httpRequest) {
        HttpMethod httpMethod = httpRequest.getHttpRequestStartLine().getHttpMethod();
        String path = httpRequest.parsingUrl();

        Map<String, String> params = httpRequest.parsingParams();
        Map<String, String> body = httpRequest.getHttpRequestBody().getBody();

        Optional<Method> controllerMethod = FrontController.getInstance().findControllerMethod(httpMethod, path);
        if (controllerMethod.isEmpty()) {
            CustomThreadLocal.onFailure(HttpStatusCode.BAD_REQUEST, ResponseUtils.makeRedirection("/error/400.html"), new byte[0]);
            return;
        }
        FrontController.getInstance().invokeFunc(controllerMethod.get(), params, body);
    }

}

package domain.user.presentation;

import common.http.response.HttpStatusCode;
import domain.user.command.application.UserCreateRequest;
import webserver.container.ResponseThreadLocal;

public class RequestValidator {
    public static boolean userCreateRequestValidate(UserCreateRequest userCreateRequest) {
        if (userCreateRequest.getUserId() == null ||
            userCreateRequest.getPassword() == null ||
            userCreateRequest.getName() == null ||
            userCreateRequest.getEmail() == null) {
            ResponseThreadLocal.onFailure(HttpStatusCode.BAD_REQUEST, null, "userId is null".getBytes());
            return false;
        }
        return true;
    }
}

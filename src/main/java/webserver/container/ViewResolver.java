package webserver.container;

import common.http.response.HttpStatusCode;
import common.utils.ResponseUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ViewResolver {

    private static final String STATIC_TEMPLATES_RESOURCE_PATH = "src/main/resources/templates";
    private static final String STATIC_STATIC_RESOURCE_PATH = "src/main/resources/static";

    private ViewResolver() {
    }

    private static class SingletonHelper {

        private static final ViewResolver SINGLETON = new ViewResolver();
    }

    public static ViewResolver getInstance() {
        return SingletonHelper.SINGLETON;
    }

    public void findModelAndView(String target) {
        byte[] body = null;
        File file;

        if (target.lastIndexOf(".html") != -1) {
            file = new File(STATIC_TEMPLATES_RESOURCE_PATH + target);
        } else {
            file = new File(STATIC_STATIC_RESOURCE_PATH + target);
        }

        if (!file.exists()) {
            CustomThreadLocal.onFailure(HttpStatusCode.FOUND, ResponseUtils.makeRedirection("/error/404.html"), new byte[0]);
            return;
        }

        try (InputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            body = outputStream.toByteArray();
        } catch (FileNotFoundException e) {
            CustomThreadLocal.onFailure(HttpStatusCode.FOUND, ResponseUtils.makeRedirection("/error/404.html"), new byte[0]);
            return;
        } catch (IOException e) {
            CustomThreadLocal.onFailure(HttpStatusCode.FOUND, ResponseUtils.makeRedirection("/error/500.html"), new byte[0]);
            return;
        }

        CustomThreadLocal.onSuccess(HttpStatusCode.OK, ResponseUtils.makeViewHeader(body.length, target), body);
    }
}

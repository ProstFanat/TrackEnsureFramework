package api.client;

import api.config.ServiceConfig;
import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class HttpClient {
    private static final Logger LOG = Logger.getLogger(HttpClient.class);

    @Step("Get {endpoint}")
    public static Response get(String endpoint, String contentType) {
        return HttpClient.sendRequest(Method.GET, endpoint, contentType);
    }

    @Step("Get {endpoint}")
    public static Response get(String endpoint, String contentType, String test) {
        return HttpClient.sendRequest(Method.GET, endpoint, contentType);
    }

    @Step("Post {endpoint} with body - {body}")
    public static Response post(String endpoint, String body, String contentType) {
        return HttpClient.sendRequest(Method.POST, endpoint, body, contentType);
    }

    @Step("Put {endpoint} with body - {body}")
    public static Response put(String endpoint, String body, String contentType) {
        return HttpClient.sendRequest(Method.PUT, endpoint, body, contentType);
    }

    @Step("Delete {endpoint}")
    public static Response delete(String endpoint, String contentType) {
        return HttpClient.sendRequest(Method.DELETE, endpoint, contentType);
    }

    private static Response sendRequest(Method method, String endpoint, String contentType) {
        return HttpClient.sendRequest(method, endpoint, null, contentType);
    }

    @Step("Send Request {method} to {endpoint} with body - {body}")
    private static Response sendRequest(Method method, String endpoint, String body, String contentType) {
        String url = ServiceConfig.HOST + endpoint;
        RequestSpecification spec = given();
        if(contentType != null) spec.contentType(contentType);
        if(body != null) spec.body(body);
        Response response = spec.request(method, url);
        LOG.info(String.format("Send %s request to %s with body : %s", method, endpoint, body));
        return response;
    }
}

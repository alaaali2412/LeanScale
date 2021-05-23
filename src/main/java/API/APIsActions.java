package API;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static io.restassured.RestAssured.given;

public class APIsActions {
    Map<String, String> sessionHeaders;

    public Map<String, String> setSessionHeaders(String key, String value) {
        this.sessionHeaders = new HashMap<>();
        sessionHeaders.put(key, value);
        return sessionHeaders;
    }

    public enum RequestType {
        POST, GET, DELETE, PUT
    }

    public Response sendRequest(RequestType requestType, String request, RequestSpecification specs) {
        switch (requestType) {
            case POST:
                return given().spec(specs).when().post(request).andReturn();
            case PUT:
                return given().spec(specs).when().put(request).andReturn();
            case GET:
                return given().spec(specs).when().get(request).andReturn();
            case DELETE:
                return given().spec(specs).when().delete(request).andReturn();
            default:
                break;
        }
        return null;
    }

    public RequestSpecification prepareRequestSpecs(String baseUri, ContentType contentType,  Map<String, String> sessionHeaders) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(baseUri).setContentType(contentType).addHeaders(sessionHeaders);
        return builder.build();
    }

    public int validateIntValuesInResponse(Response response, String ValueToValidate) {
        JsonPath path = response.jsonPath();
        return path.get(ValueToValidate);
    }

    public Response prepareGetAPIResponse(String baseURI,String serviceName, RequestType requestType, ContentType contentType,
                                         Map<String, String> sessionHeaders) {
        return sendRequest(requestType, serviceName, prepareRequestSpecs(baseURI, contentType, sessionHeaders));
    }
}

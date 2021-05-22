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

    public RequestSpecBuilder initializeBuilder(String baseUri, ContentType contentType) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(baseUri).setContentType(contentType);
        return builder;
    }

    public RequestSpecification prepareRequestSpecs(String baseUri, ContentType contentType ) {
        RequestSpecBuilder builder = initializeBuilder(baseUri, contentType);
        return builder.build();
    }

    public ResponseSpecBuilder responseSpecBuilder() {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        return responseSpecBuilder;
    }


    public int validateValueInResponse(Response response, String ValueToValidate) {
        JsonPath path = response.jsonPath();
        return path.get(ValueToValidate);
    }

    public enum RequestType {
        POST, GET, DELETE, PUT
    }

    public String getParamInJsonArray(JsonPath path, String ValueToValidate, String list) {
        List<HashMap<String, Object>> data = path.getList(list);
        for (HashMap<String, Object> singleObject : data) {
            if (singleObject.containsValue(ValueToValidate)) {
                return singleObject.toString();
            }
        }
        throw new NoSuchElementException("Can't find param ");
    }

    public int getIntValueFromArray(String arrayToSplit, String firstIndex, String secondIndex) {
        String[] parts = arrayToSplit.split(firstIndex);
        String[] part = parts[0].split(secondIndex);
        return Integer.parseInt(part[1]);
    }

    public Response prepareGetAPIsResponse(String baseURI, RequestType requestType, String serviceName, ContentType contentType) {
        return sendRequest(requestType, serviceName, prepareRequestSpecs(baseURI, contentType));
    }
}

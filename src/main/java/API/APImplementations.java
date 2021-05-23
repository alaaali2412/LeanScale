package API;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.core.Is;
import org.testng.Assert;

import static org.hamcrest.Matchers.*;

public class APImplementations extends APIsActions {

    String baseUri = "https://m2.leanscale.com/";
    String serviceName = ServiceNames.leanScaleCategories.getServiceName();
    String token = "uulbfv3bozwp0wbvyax39o0348elq21f";

    public Response apiResponse() {
        return prepareGetAPIResponse(baseUri, serviceName, RequestType.GET, ContentType.JSON,
                setSessionHeaders("Authorization", "Bearer " + token));
    }

    public void checkApiStatusCode() {
        Assert.assertEquals(apiResponse().getStatusCode(), 200);
    }


    public void validateResponseBody(){
        apiResponse().then().assertThat().body("name",  Is.is("Default Category"));
        apiResponse().then().assertThat().body("id",  Is.is(2));
        apiResponse().then().assertThat().body("parent_id",  Is.is(1));
        for (int i = 1; i< validateIntValuesInResponse(apiResponse(), "children_data.size"); i++ ){
            apiResponse().then().assertThat().body("children_data["+i+"]",notNullValue() );
        }
    }
}

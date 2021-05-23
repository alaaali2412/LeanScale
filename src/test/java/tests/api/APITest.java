package tests.api;

import API.APImplementations;
import org.testng.annotations.Test;

public class APITest {
    APImplementations apImplementations = new APImplementations();
    @Test
    public void validateApiResponseStausCode(){

        apImplementations.checkApiStatusCode();
    }

    @Test
    public void validateAPiResponseBodyHasData(){
        apImplementations.validateResponseBody();
    }
}

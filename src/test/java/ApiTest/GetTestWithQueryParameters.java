package ApiTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetTestWithQueryParameters {
    private static final Logger LOGGER = LogManager.getLogger( GetTestWithQueryParameters.class);

    @Test
    public void getUsersWithQueryParameter(){
        LOGGER.info("-----------API Test: Get All  With Query Parameter-------------");

        RestAssured.baseURI = "https://reqres.in/api/users";

        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.request(Method.GET);

        Response response = httpRequest.queryParam("page","2").request(Method.GET);
        LOGGER.debug(response.prettyPrint());

        Assert.assertEquals(response.getStatusCode(),200);
        JsonPath jsonPath = response.jsonPath();
        List<String> listEmails = jsonPath.get("data.email");

        String expectedEmailId ="michael.lawson@reqres.in";

        boolean emailExists = listEmails.contains(expectedEmailId);

        Assert.assertTrue(emailExists,expectedEmailId + "does not exist");

        LOGGER.info("-----------End API Test: Get All Users With Query Parameter-------------");


    }

}



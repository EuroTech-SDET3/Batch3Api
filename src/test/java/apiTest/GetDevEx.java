package apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class GetDevEx {


    String baseURL="http://92.205.106.232";
    String petURL="https://petstore.swagger.io/v2";

    @Test
    public void test1() {

        /*
        Given accept type is Json(devex)
        When user sends GET request to /api/profile
        Then verify that response status code is 200
        and body is json format
        and response body contains Melba Crooks PhD
         */
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseURL + "/api/profile");

        //verify status code 200
        assertEquals(response.statusCode(),200);

        //verify that body is json
        assertEquals(response.contentType(),"application/json; charset=utf-8");

        //verify body contains Melba Crooks PhD
        assertTrue(response.body().asString().contains("Melba Crooks PhD"));
    }

    @Test
    public void headersTest() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(baseURL + "/api/profile");

        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));
        System.out.println("response.header(\"Keep-Alive\") = " + response.header("Keep-Alive"));

        //verify the date
        System.out.println("response.headers().hasHeaderWithName(\"Date\") = " + response.headers().hasHeaderWithName("Date"));
        assertTrue(response.headers().hasHeaderWithName("Date"));
        System.out.println("response.header(\"ETag\") = " + response.header("ETag"));

    }
}

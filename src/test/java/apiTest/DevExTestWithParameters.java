package apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static org.testng.Assert.*;

public class DevExTestWithParameters {



//    @BeforeClass
//    public void beforeClass(){
//        baseURI="http://92.205.106.232";
//    }

//    @Test
//    public void test1() {
//        get(baseURI+"/api/profile");
//    }
    String baseURL="http://92.205.106.232";
    String petURL="https://petstore.swagger.io/v2";


    @Test
    public void pathParamPetStore1() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(petURL + "/pet/9");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

    }

    @Test
    public void pathParamPetStore2() {


        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 9)
                .when().get(petURL + "/pet/{id}");
        System.out.println("response.statusCode() = " + response.statusCode());
    }

    @Test
    public void pathParamDevex() {
        /*
        Given accept type is Json
        When user sends GET request to /api/profile/user/{userID}
        id=34
        Then verify that response status code is 200
        verify that body contains sdet_blg@gmail.com
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("userID", 34)
                .when().get(baseURL + "/api/profile/user/{userID}");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        assertTrue(response.body().asString().contains("sdet_blg@gmail.com"));
    }

    @Test
    public void queryParam() {
        /*
        TASK
        Given accept type is Json
        And query  parameter is status available
        When user sends GET request to /pet/findByStatus
        The response status code 200
        And content type is application/json
        And "fish" should be in payload/ body
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("status", "available")
                .when().get(petURL + "/pet/findByStatus");

        response.prettyPrint();

        //verify that status code 200
        assertEquals(response.statusCode(),200);

        //verify that content type application/json
        assertEquals(response.contentType(),"application/json");

        //verify that body contains fish
        assertTrue(response.body().asString().contains("fish"));
    }

    @Test
    public void mapWithAPI() {

        Map<String,Object> queryMap= new HashMap<>();
        queryMap.put("status","available");
       // queryMap.put("status","sold");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get(petURL + "/pet/findByStatus");
        response.prettyPrint();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("fish"));
    }

    @Test
    public void queryDevex() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("company", "Amazon")
                .when().get(baseURL + "/api/profile/userQuery");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        assertTrue(response.body().asString().contains("Amazon"));
    }
}

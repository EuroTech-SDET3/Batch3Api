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

public class DevExTestWithPath {

    String baseURL="http://92.205.106.232";
    String petURL="https://petstore.swagger.io/v2";


    @Test
    public void getOneUser() {

        /*
        "id": 34,
        "email": "sdet_blg@gmail.com",
       "name": "Blg",
       "company": "Amazon",
        "status": "Junior Developer",

         */
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("userID", 34)
                .when().get(baseURL + "/api/profile/user/{userID}");

     //   response.prettyPrint();

        assertEquals(response.statusCode(),200);

        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"email\") = " + response.path("email"));
        System.out.println("response.path(\"company\") = " + response.path("company"));
        System.out.println("response.path(\"status\") = " + response.path("status"));

        int actualUserId = response.path("id");
        String  actualEmail = response.path("email");
        String actualCompany=response.path("company");
        String actualStatus=response.path("status");

        assertEquals(actualUserId,34);
        assertEquals(actualEmail,"sdet_blg@gmail.com");
        assertEquals(actualCompany,"Amazon");
        assertEquals(actualStatus,"Junior Developer");
    }

    @Test
    public void getOnePet() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 9)
                .when().get(petURL + "/pet/{id}");

        response.prettyPrint();

        //verify id is 9

        int id = response.path("id");
        assertEquals(id,9);

        //verify name is cats
        System.out.println("response.path(\"name\") = " + response.path("category.name"));
        assertEquals(response.path("category.name"),"cats");

        //verify status is sold
        assertEquals(response.path("status"),"sold");
    }
}

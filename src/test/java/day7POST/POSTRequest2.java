package day7POST;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class POSTRequest2 {

        Faker fk = new Faker();

    @BeforeClass
    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }

    @Test
    public void postNewUser() {

        //Create new User
        //Verify with Token
        //Verify that user info's

        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("email","hamilton@mynet.com");
        requestMap.put("password","Pas123");
        requestMap.put("name","Hamilton");
        requestMap.put("google","HamGol");
        requestMap.put("facebook","FaceHam");
        requestMap.put("github","FaceGit");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .when()
                .post("/api/users");

        assertEquals(response.statusCode(),200);

        String token = response.path("token");

        String profileBody ="{\n" +
                "    \"skills\": \"ReactsJS, HTML, Javascript\",\n" +
                "    \"status\": \"Automation Test Engineer\",\n" +
                "    \"company\": \"TCS\",\n" +
                "    \"website\": \"thelipagus.com\",\n" +
                "    \"bio\": \"I am a developer\",\n" +
                "    \"githubUserName\": \"theliapgus\",\n" +
                "    \"twitter\": \"twitter.com\",\n" +
                "    \"facebook\": \"facebook.com\",\n" +
                "    \"instagram\": \"instagram.com\"\n" +
                "}";


     response=   given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("x-auth-token",token)
                .and()
                .body(profileBody)
                .when()
                .post("/api/profile");

     assertEquals(response.statusCode(),200);

     response.prettyPrint();



    }

    @Test
    public void postNewUserAndVerify() {

        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("email","john@yahoo.com");
        requestMap.put("password","Pas123");
        requestMap.put("name","John Smith");
        requestMap.put("google","JohnGoogle");
        requestMap.put("facebook","JohnFace");
        requestMap.put("github","SmithGit");

        //name, email, google and facebook kisimlarini da verify et.

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .when()
                .post("/api/users");

        assertEquals(response.statusCode(),200);

        String token = response.path("token");

        String profileBody ="{\n" +
                "    \"skills\": \"ReactsJS, HTML, Javascript\",\n" +
                "    \"status\": \"Junior Developer\",\n" +
                "    \"company\": \"TCS\",\n" +
                "    \"website\": \"thesmith.com\",\n" +
                "    \"bio\": \"I am a developer\",\n" +
                "    \"githubUserName\": \"theliapgus\",\n" +
                "    \"twitter\": \"twitter.com\",\n" +
                "    \"facebook\": \"facebook.com\",\n" +
                "    \"instagram\": \"instagram.com\"\n" +
                "}";


        response=   given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("x-auth-token",token)
                .and()
                .body(profileBody)
                .when()
                .post("/api/profile");

        assertEquals(response.statusCode(),200);

        response.prettyPrint();

      String actualName=  response.path("user.name");
      String actualEmail= response.path("user.email");
      String actualGoogle= response.path("user.google");
      String actualFacebook= response.path("user.facebook");

      assertEquals(actualName,"John Smith");
      assertEquals(actualEmail,"john@yahoo.com");
      assertEquals(actualFacebook,"JohnFace");
      assertEquals(actualGoogle,"JohnGoogle");


    }

    @Test
    public void newUserPost() {
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("email","Isercan@yahoo.com");
        requestMap.put("password","Pas123");
        requestMap.put("name","Ayse Smith");
        requestMap.put("google","AyseGoogle");
        requestMap.put("facebook","AyseFace");
        requestMap.put("github","AyseGit");

        //name, email, google and facebook kisimlarini da verify et.

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .when()
                .post("/api/users");

        assertEquals(response.statusCode(),200);

        String token = response.path("token");

        String profileBody ="{\n" +
                "    \"skills\": \"ReactsJS, HTML, Javascript\",\n" +
                "    \"status\": \"Junior Developer\",\n" +
                "    \"company\": \"TCS\",\n" +
                "    \"website\": \"thesmith.com\",\n" +
                "    \"bio\": \"I am a developer\",\n" +
                "    \"githubUserName\": \"theliapgus\",\n" +
                "    \"twitter\": \"twitter.com\",\n" +
                "    \"facebook\": \"facebook.com\",\n" +
                "    \"instagram\": \"instagram.com\"\n" +
                "}";


        response=   given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("x-auth-token",token)
                .and()
                .body(profileBody)
                .when()
                .post("/api/profile");

        assertEquals(response.statusCode(),200);

     //   response.prettyPrint();

        int id = response.path("user.id");

        response = given().accept(ContentType.JSON)
                .and()
                .pathParam("userID", id)
                .when().get("/api/profile/user/{userID}");

        response.prettyPrint();

        //hamcrest Matcher ile verify etme
        given().accept(ContentType.JSON)
                .and()
                .pathParam("userID", id)
                .when().get("/api/profile/user/{userID}").
                then().assertThat().body("email",Matchers.equalTo("john@yahoo.com"),
                        "name",Matchers.equalTo("John"));

    }
}

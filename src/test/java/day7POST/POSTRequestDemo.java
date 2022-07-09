package day7POST;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class POSTRequestDemo {



    @BeforeClass
    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }


    @Test
    public void postNewUser() {

        String jsonBody ="{\n" +
                "  \"email\": \"canpare@yahoo.com\",\n" +
                "  \"password\": \"Pas4532\",\n" +
                "  \"name\": \"Can\",\n" +
                "  \"google\": \"CanGoogle\",\n" +
                "  \"facebook\": \"CanFace\",\n" +
                "  \"github\": \"CanJack\"\n" +
                "}";


        Response response = given().accept(ContentType.JSON) //bana json body gonder
                .and()
                .contentType(ContentType.JSON) //ben sana json body gonderiyorum.
                .and()
                .body(jsonBody)  //
                .when()
                .post("/api/users");

        assertEquals(response.statusCode(),200);

        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser2() {

        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("email","gomez@mynet.com");
        requestMap.put("password","Pas123");
        requestMap.put("name","Gomez");
        requestMap.put("google","GomezleTal");
        requestMap.put("facebook","FaceGomez");
        requestMap.put("github","GitGomez");


        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .when()
                .post("/api/users");

        assertEquals(response.statusCode(),200);

        assertTrue(response.body().asString().contains("token"));

        System.out.println("token = " + response.path("token"));

    }

    @Test
    public void postNewUser3() {
        NewUserInfo newUser = new NewUserInfo();
            newUser.setEmail("heMan@yahoo.com");
            newUser.setPassword("Pass123");
            newUser.setName("HeMAN");
            newUser.setGoogle("HeManGoogle");
            newUser.setFacebook("HeManFace");
            newUser.setGithub("HeManGithub");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUser)
                .when()
                .post("/api/users");

        assertEquals(response.statusCode(),200);
        System.out.println("response.path(\"token\") = " + response.path("token"));

    }

    @Test
    public void postNewUser4() {
        NewUserInfo newUser = new NewUserInfo("angelina@mynet.com","Pas321","Angelina","AngelGoogle","AngelFace","AngelGit");
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUser)
                .when()
                .post("/api/users");

        assertEquals(response.statusCode(),200);
        System.out.println("response.path(\"token\") = " + response.path("token"));

    }
}

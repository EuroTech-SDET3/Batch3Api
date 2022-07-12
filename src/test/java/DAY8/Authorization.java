package DAY8;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Authorization {

    @BeforeClass
    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }


    @Test
    public void test1(){

        String loginBody="{\n" +
                "  \"email\": \"etsMntr@eurotech.com\",\n" +
                "  \"password\": \"Test123+\"\n" +
                "}";

        Response response=given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(loginBody)
                .when()
                .post("/api/auth");


        assertEquals(response.statusCode(),200);

        String token=response.path("token");
        System.out.println("token = " + token);


    }

    @Test
    public static String getToken(){

       //json body göndermek için bir tane map oluştur
        Map<String,Object> tokenMap=new HashMap<>();
        tokenMap.put("email","etsMntr@eurotech.com");
        tokenMap.put("password","Test123+");

        Response response=given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(tokenMap)
                .when()
                .post("/api/auth");


        assertEquals(response.statusCode(),200);

        String token=response.path("token");
        System.out.println("token = " + token);

            return token;
    }

    @Test
    public static  Map<String,Object> getAccessToken(String email,String password){
        Map<String,Object> getTokenMap= new HashMap<>();
        getTokenMap.put("email",email);
        getTokenMap.put("password",password);


        Response response=given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(getTokenMap)
                .when()
                .post("/api/auth");

        String token=response.path("token");
        System.out.println("token = " + token);


        Map<String,Object> authorization= new HashMap<>();
        authorization.put("x-auth-token",token);

        return authorization;
    }




}

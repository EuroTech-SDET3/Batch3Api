package apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static org.testng.Assert.assertEquals;

public class DevExJsonToJava {

    @BeforeClass
    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }

    /*
    {
    "id": 9,
    "email": "Erling.Okuneva@yahoo.com",
    "name": "Ms. Keith Pacocha",
    "company": "TCS",
    "status": "Senior Developer",
    "profileId": 12
}
     */

    @Test
    public void test1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("userID", 9)
                .when().get( "/api/profile/user/{userID}");

        assertEquals(response.statusCode(),200);
        //response.prettyPrint();

        //converting json to java--> json responsu java map e dönüştürme
        Map<String,Object> jsonDataMap=response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);


        double id= (double)jsonDataMap.get("id");
        System.out.println("id = " + id);
        assertEquals(id,9);
        // kalan body için assert edilmesi ödev


    }

    @Test
    public void test2(){
        Response response = get("/api/profile");
        // response.prettyPrint();


        //de-serialization Json responsu List of Map

        List<Map<String,Object>> allBody=response.body().as(List.class);
        System.out.println("allBody = " + allBody);

        //print second company name
        System.out.println("allBody.get(1).get(\"company\") = " + allBody.get(1).get("company"));

        Map<String,Object> body3=allBody.get(2);
        System.out.println("body3 = " + body3);


    }




}

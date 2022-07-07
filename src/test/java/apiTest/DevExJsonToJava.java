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

    @Test
    public void getOneUser() {
/*
{
7 siradaki user i getirip bilgilerini verify edelim
    "id": 34,
    "email": "sdet_blg@gmail.com",
    "name": "Blg",

 */
        Response response = get("/api/profile");

        assertEquals(response.statusCode(),200);

       // response.prettyPrint(); //json formatinda

        List<Map<String,Object>> allProfilesList = response.body().as(List.class); //java
        System.out.println("allProfilesList.get(6).get(\"company\") = " + allProfilesList.get(6).get("company"));
        System.out.println("allProfilesList.get(6).get(\"email\") = " + allProfilesList.get(6).get("email"));

        //print Bilge info
        Map<String,Object> userInfo = (Map<String, Object>) allProfilesList.get(6).get("user");
        System.out.println("userInfo.get(\"id\") = " + userInfo.get("id"));
        System.out.println("userInfo.get(\"email\") = " + userInfo.get("email"));
        System.out.println("userInfo.get(\"name\") = " + userInfo.get("name"));
        System.out.println("userInfo.get(\"company\") = " + userInfo.get("company")); //null gelecektir



    }

    @Test
    public void task() {

        /*
        /api/profile endPoint ile gelen response
        //3. kullanicinin edu kismindaki school olan
        1 : ITU
        2 : eurotech Study
        bilgilerini verify et.
         */
        Response response = get("/api/profile");

        List<Map<String, Object>> allBody = response.body().as(List.class);


        List<Map<String, Object>> body3_education = (List<Map<String, Object>>) allBody.get(2).get("education");
        System.out.println("body3_education = " + body3_education);

        String body3_education_1 = (String) body3_education.get(0).get("school");
        System.out.println("body3_education_1 = " + body3_education_1);
        assertEquals(body3_education_1, "ITU");

        String body3_education_2 = (String) body3_education.get(1).get("school");
        System.out.println("body3_education_2 = " + body3_education_2);
        assertEquals(body3_education_2, "eurotech Study");


    }
}

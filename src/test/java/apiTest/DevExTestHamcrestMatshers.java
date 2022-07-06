package apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class DevExTestHamcrestMatshers {

    @BeforeClass
    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }

     /*
        TASK
          Given accept type is json(DevEx)
          And path param "userID" is
          When user sends a get request to /api/profile/user/{userID}
         Then status code is 200
         And content type is application/json
         And
            "id": 25,
            "email": "jrdev@gmail.com",
             "name": "Jr. Dev",
            "company": "google",
            "status": "Junior Developer",
    */

    @Test
    public void getOneUser() {
        //request
        given().accept(ContentType.JSON)
                .pathParam("userID", 25)
                .when()
                .get("/api/profile/user/{userID}")
                //response
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .contentType("application/json; charset=utf-8");


    }

    @Test
    public void getOneUser_HamcrestMatcher() {
        //request
        given().accept(ContentType.JSON)
                .pathParam("userID", 25)
                .when()
                .get("/api/profile/user/{userID}")
                //response
                .then().assertThat()
                .statusCode(200)
                .and().assertThat().contentType("application/json; charset=utf-8")
                .and().assertThat()
                .body("id", Matchers.equalTo(25),
                        "email", Matchers.equalTo("jrdev@gmail.com"),
                        "name", Matchers.equalTo("Jr. Dev"),
                        "company", Matchers.equalTo("google"),
                        "status", Matchers.equalTo("Junior Developer")
                );


    }

    /*
     /*
    {
    "id": 10,
    "email": "eurotech@gmail.com",
    "name": "Eurotech",
    "company": "euroTech Study",
    "status": "Instructor",
    "profileId": 3
}
     */


    @Test
    public void test2(){

        given().accept(ContentType.JSON)
                .pathParam("userID",10)
                .when()
                .log().all()   // request ve response arasına .log() method koyarsak body i consolda görebiliriz
                .get("/api/profile/user/{userID}")
                .then().assertThat().statusCode(200)
                .and().contentType(equalTo("application/json; charset=utf-8"))
                .and()
                .header("Content-Length",equalTo("119"))
                .and()
                .header("Connection",equalTo("keep-alive"))
                .and()
                .header("Date",notNullValue())
                .and()
                .assertThat()
                .body("id",equalTo(10),
                        "email",equalTo("eurotech@gmail.com"),
                        "name",equalTo("Eurotech"),
                        "company",equalTo("euroTech Study"),
                        "status",equalTo("Instructor")
                     ).log().all();


    }


    @Test
    public void test3(){

        given().accept(ContentType.JSON)
                .when()
                .log().all()
                .get("/api/profile")
                .then()
                .assertThat()
                .statusCode(200)
                .and().contentType(equalTo("application/json; charset=utf-8"))
                .and()
                .header("Content-Type",equalTo("application/json; charset=utf-8"))
                .and()
                .header("Keep-Alive",equalTo("timeout=5"))
                .and()
                .header("Content-Length",equalTo("12214"))
                .and().assertThat()
                .body("user.id[2]",equalTo(10),
                        "education[2].school[1]",equalTo("eurotech Study"),
                        "experience[2].company[2]",equalTo("eurotech")
                )
                .log().all();


    }

    @Test
    public void test4(){
        given().accept(ContentType.JSON)
                .when()
                .log().all()
                .get("/api/profile")
                .then().assertThat().statusCode(200)
                .and()
                .contentType(equalTo("application/json; charset=utf-8"))
                .and()
                .body("user.name",hasItem("TestAccount"))
                .log().all();




    }


}

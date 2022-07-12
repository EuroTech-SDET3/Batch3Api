package DAY8;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ExperienceFlow {



    @BeforeClass
    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }

@Test
    public void postExperience(){
    /*
    {
  "title": "string",
  "company": "string",
  "location": "string",
  "from": "YYYY-MM-DD",
  "to": "YYYY-MM-DD",
  "current": false,
  "description": "string"
}
     */


    Map<String,Object> addExperience= new HashMap<>();
    addExperience.put("title","QA");
    addExperience.put("company","QAland");
    addExperience.put("location","Hamburg");
    addExperience.put("from","2021-01-01");
    addExperience.put("to","2021-10-10");
    addExperience.put("current",false);
    addExperience.put("description","hello");

    given().log().all()
            .and()
            .contentType(ContentType.JSON)
            .and()
            .header("x-auth-token",Authorization.getToken())
            .and()
            .body(addExperience)
            .when()
            .post("/api/profile/experience")
            .then().log().all()
            .assertThat().statusCode(201);


}

@Test
    public void updateExperiencePUT_method(){

        /*
        {
  "title": "SDET",
  "company": "SDET Land",
  "location": "Berlin",
  "from": "2021-10-10",
  "to": "2022-07-12",
  "current": true,
  "description": "One year"
}
         */

    Map<String,Object> updateExperience= new HashMap<>();
    updateExperience.put("title","SDET-QA");
    updateExperience.put("company","SDET-QAland");
    updateExperience.put("location","Hamburg");
    updateExperience.put("from","2021-10-10");
    updateExperience.put("to","2022-07-12");
    updateExperience.put("current",true);
    updateExperience.put("description","hello one year");


    given().log().all()
            .and()
            .contentType(ContentType.JSON)
            .and()
            //.header("x-auth-token",Authorization.getToken())
            .headers(Authorization.getAccessToken("etsMntr@eurotech.com","Test123+"))
            .and()
            .body(updateExperience)
            .when()
            .put("/api/profile/experience/18")
            .then().log().all()
            .assertThat().statusCode(204);



}

@Test
    public void updateExperiencePATCH_method(){

    Map<String,Object> updateExperience= new HashMap<>();
    updateExperience.put("title","PATCH-SDET-QA-2");
    updateExperience.put("company","PATCH-SDET-QAland-2");


    given().log().all()
            .and()
            .contentType(ContentType.JSON)
            .and()
            .headers(Authorization.getAccessToken("etsMntr@eurotech.com","Test123+"))
            .and()
            .pathParam("id",18)
            .body(updateExperience)
            .when()
            .patch("/api/profile/experience/{id}")
            .then().log().all()
            .assertThat().statusCode(204);


}


@Test
    public void deleteExperience(){

        given().log().all()
                .and().contentType(ContentType.JSON)
                .and()
                .headers(Authorization.getAccessToken("etsMntr@eurotech.com","Test123+"))
                .and()
                .pathParam("id",46)
                .and()
                .delete("/api/profile/experience/{id}")
                .then()
                .assertThat().statusCode(200)
                .log().all();


}

@Test
    public void getExperience(){

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .headers(Authorization.getAccessToken("etsMntr@eurotech.com","Test123+"))
                .and()
                .pathParam("id",46)
                .and()
                .get("/api/profile/experience/{id}")
                .then()
                .assertThat().statusCode(200)
                .log().all();

}


}

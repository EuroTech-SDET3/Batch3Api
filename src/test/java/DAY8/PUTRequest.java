package DAY8;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PUTRequest {



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
    public void test2(){

}


}

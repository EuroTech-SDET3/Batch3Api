package apiTest;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;


import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class DevExWithJsonPath {

    String baseURL="http://92.205.106.232";
    /*
    TASK
          Given accept type is json(DevEx)
          And path param "userID" is
          When user sends a get request to /api/profile/user/{userID}
         Then status code is 200
         And content type is application/json
         And
         {
            "id": 25,
            "email": "jrdev@gmail.com",
            "name": "Jr. Dev",
             "company": "google",
             "status": "Junior Developer",

}

     */


    @Test
    public void getOneUser(){
        Response response= given().accept(ContentType.JSON)
                .and().pathParam("userID",25)
                .when().get(baseURL+"/api/profile/user/{userID}");

        assertEquals( response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");

        response.prettyPrint();

        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"email\") = " + response.path("email"));
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"company\") = " + response.path("company"));
        System.out.println("response.path(\"status\") = " + response.path("status"));

        //verify id and name with path

        int actualUserId=response.path("id");
        String actualName=response.path("name");

        assertEquals(actualUserId,25);
        assertEquals(actualName,"Jr. Dev");


        System.out.println("*****************************");

        // response to JsonPath : jsonPath bize birçok faydalı methodlar kullanma imkanı sunuyor.

        JsonPath jsonData=response.jsonPath();

        int userID_json=jsonData.getInt("id");
        String email_Json=jsonData.getString("email");
        String name_Json=jsonData.getString("name");
        String company_Json=jsonData.getString("company");
        String status_json=jsonData.getString("status");

        System.out.println("userID_json = " + userID_json);
        System.out.println("email_Json = " + email_Json);
        System.out.println("name_Json = " + name_Json);
        System.out.println("company_Json = " + company_Json);
        System.out.println("status_json = " + status_json);

        // assertion with jsonPath

        assertEquals(userID_json,25);
        assertEquals(email_Json,"jrdev@gmail.com");
        assertEquals(name_Json,"Jr. Dev");
        assertEquals(company_Json,"google");
        assertEquals(status_json,"Junior Developer");




    }

    @Test
    public void test2(){

        Response response= get(baseURL+"/api/profile");

        assertEquals(response.statusCode(),200);

        // assinging to jsonPath with index
        JsonPath jsonData=response.jsonPath();

        //System.out.println("jsonData.getInt(\"id[1]\") = " + jsonData.getInt("id[1]"));

        int secondID_Json= jsonData.getInt("id[1]");
        System.out.println("secondID_Json = " + secondID_Json);

        //get all company name
        System.out.println("get all company name");


        List<String> allCompany_Json=jsonData.getList("company");
        System.out.println("allCompany_Json = " + allCompany_Json);


        //Map with JsonPath
        System.out.println("Map with JsonPath");

        Map<String,Object> secondUserMap_Json=jsonData.getMap("user[1]");

        System.out.println("secondUserMap_Json = " + secondUserMap_Json);



        //List with jsonPath (skills)
        System.out.println("List with jsonPath (skills)");

        List<String> secondSkills_Json= jsonData.getList("skills[1]");
        for (String secondSkills : secondSkills_Json) {
            System.out.println("secondSkills = " + secondSkills);
        }

        System.out.println("secondSkills_Json = " + secondSkills_Json);

        //get all user name where their github addres are equals to null

        List<String> user_Json=jsonData.getList("user.findAll{it.github==null}.name");
        System.out.println("user_Json = " + user_Json);

    }



}

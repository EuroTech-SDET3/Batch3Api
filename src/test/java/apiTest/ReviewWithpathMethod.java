package apiTest;

import static io.restassured.RestAssured.*;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ReviewWithpathMethod {

//@BeforeClass
//        public void beforeClass(){
//
//    baseURI="http://92.205.106.232";
//
//}
    String baseURL="http://92.205.106.232";

    @Test
    public void getAllCompanyWithIndex_Path(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .when().get(baseURL+"/api/profile");

        //verify status code
        assertEquals(response.statusCode(),200);
        //verify Content-Type
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        System.out.println("response.contentType() = " + response.contentType());

        assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");
        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));

        assertEquals(response.getHeader("Content-Type"),"application/json; charset=utf-8");
        System.out.println("response.getHeader(\"Content-Type\") = " + response.getHeader("Content-Type"));

            // path method ile birden fazla "id" ye sahip olduğumuz için index numarası ile request gönderebiliriz.

        System.out.println(response.path("id[2]").toString());

        int firstId=response.path("id[0]");
        System.out.println("firstId = " + firstId);

        int lastId=response.path("id[-1]");
        System.out.println("lastId = " + lastId);

        String firstCompany=response.path("company[0]");
        System.out.println("firstCompany = " + firstCompany);

        String lastCompany=response.path("company[-1]");
        System.out.println("lastCompany = " + lastCompany);

        System.out.println("*********************************************");
        //Json body içerisinde bir list için index nasıl kullanılır?

        List<String> firstSkills=response.path("skills[0]");
        for (String firstSkill : firstSkills) {
            System.out.println("firstSkill = " + firstSkill);
        }

        System.out.println("********************************");
        // list içerisinde index sırasına göre ilk skills

        String skillsIndexOne = response.path("skills[0][0]");
        System.out.println("skillsIndexOne = " + skillsIndexOne);

        Object skillsIndexTwo = response.path("skills[0][1]");
        System.out.println("skillsIndexTwo = " + skillsIndexTwo);


        System.out.println("***********************************");
        //json body içerisinde bir map için index nasıl kullanılır?

        Map<String,Object> firstUserMaps= response.path("user[0]");
        System.out.println("firstUserMaps = " + firstUserMaps);

        for (String firstUserMap : firstUserMaps.keySet()) {
            System.out.println("firstUserMap = " + firstUserMap);

        }

        Object firstUserMap2 = response.path("user[0]");
        System.out.println("firstUserMap2 = " + firstUserMap2);

        System.out.println("******************************************");


        Object firstUserId = response.path("user[0].id");
        System.out.println("firstUserId = " + firstUserId);

        Object firstUserId2 = response.path("user.id[0]");
        System.out.println("firstUserId2 = " + firstUserId2);

        String firstname= response.path("user.name[0]");
        System.out.println("firstname = " + firstname);

        // all id
        List<Integer> allID=response.path("id");
        System.out.println("allID = " + allID);
        System.out.println("allID.size() = " + allID.size());

        //all user id
        List<Integer> allUserID=response.path("user.id");
        for (Integer eachUserID : allUserID) {
            System.out.println("eachUserID = " + eachUserID);
        }

        //print all company from devEx profile
        //index 3 . sıradaki company


    }


}

package apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class simpleGetRequest {


    String devExUrl="http://92.205.106.232";
    String petUrl="";

    @Test
    public void test1() {

        Response response = RestAssured.get(devExUrl+"/api/profile");

        //print status code
        System.out.println("response.statusCode() = " + response.statusCode());


        //print body
        response.prettyPrint();

    }

    @Test
    public void test2() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                           .when().get(devExUrl + "/api/profile");

        //verify that status code 200
        Assert.assertEquals(response.statusCode(),200);

        //print content type is application/json
        System.out.println("response.contentType() = " + response.contentType());

        //verify that content type is application/json; charset=utf-8
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

        //print body
        response.prettyPrint();

    }

    @Test
    public void test3() {
        //RestAssured kullanarak verify etme.

        RestAssured.given().accept(ContentType.JSON).
                           when().get(devExUrl+"/api/profile").then().
                           assertThat().statusCode(200).
                           and().contentType("application/json; charset=utf-8");

    }

    @Test
    public void test4() {

        //body icerisinde oykugit oldugubu verify et

        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(devExUrl + "/api/profile");

        Assert.assertEquals(response.statusCode(),200);

        //body nin hepsini string e cevirdi
        System.out.println("response.body().asString() = " + response.body().asString());

        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

        Assert.assertTrue(response.body().asString().contains("oykugit"));


    }
}

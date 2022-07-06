package apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.baseURI;

public class PetStoreWithJsonPath {

    /*
    {
    "id": 14,
    "category": {
        "id": 34,
        "name": "Kangal"
    },
    "name": "Black White",
    "photoUrls": [
        "string"
    ],
    "tags": [
        {
            "id": 0,
            "name": "small"
        },
        {
            "id": 1,
            "name": "medium"
        },
        {
            "id": 3,
            "name": "big"
        }
    ],
    "status": "available"
}
     */

    @BeforeClass
    public void beforeClass(){
        baseURI="https://petstore.swagger.io/v2";
    }

    @Test
    public void getPet(){
        Response response= RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("status","sold")
                .when().get("/pet/findByStatus");

        //response.prettyPrint();

        //assertion status code
        assertEquals(response.statusCode(),200);

        //assign response jsonPath
        JsonPath jsonData=response.jsonPath();

        //get value from jsonpath

        String allCategoryName=jsonData.getString("category.name");
        System.out.println("allCategoryName = " + allCategoryName);

        String categoryName=jsonData.getString("category[0].name");
        System.out.println("categoryName = " + categoryName);

        List<String> tagName= jsonData.getList("tags.findAll{it.name[0]==\"KT\"}.id");
        System.out.println("tagName = " + tagName);


    }


}

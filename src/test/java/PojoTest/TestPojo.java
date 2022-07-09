package PojoTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static org.testng.Assert.assertEquals;

public class TestPojo {
    @BeforeClass
    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }

    @Test
    public void testName() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("userID", 34)
                .when().get(baseURI + "/api/profile/user/{userID}");

        assertEquals(response.statusCode(),200);

        //https://www.jsonschema2pojo.org/ sitesinden yapiliyor

        com.example.JsonSchemaUser test = response.body().as(com.example.JsonSchemaUser.class);
        System.out.println("test.getEmail() = " + test.getEmail());
    }
}

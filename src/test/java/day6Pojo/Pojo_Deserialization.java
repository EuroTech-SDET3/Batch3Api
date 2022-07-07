package day6Pojo;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static org.testng.Assert.assertEquals;

public class Pojo_Deserialization {

    @BeforeClass
    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }

    @Test
    public void oneEurotechUser() {
    /*
        {
            "id": 34,
                "email": "sdet_blg@gmail.com",
                "name": "Blg",
                "company": "Amazon",
                "status": "Junior Developer",
                "profileId": 7
        }

     */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("userID", 34)
                .when().get(baseURI + "/api/profile/user/{userID}");

        assertEquals(response.statusCode(),200);

        //JSON to our Eurotech class object
        EurotechUser oneUser = response.body().as(EurotechUser.class);
        System.out.println("oneUser.getId() = " + oneUser.getId()); //34.0
        System.out.println("oneUser.getEmail() = " + oneUser.getEmail());
        System.out.println("oneUser.getCompany() = " + oneUser.getCompany());
        System.out.println("oneUser.getStatus() = " + oneUser.getStatus());
        System.out.println("oneUser.getProfileId() = " + oneUser.getProfileId());

        assertEquals(oneUser.getId(),34.0);
        assertEquals(oneUser.getEmail(),"sdet_blg@gmail.com");
        assertEquals(oneUser.getCompany(),"Amazon");
        assertEquals(oneUser.getStatus(),"Junior Developer");
        assertEquals(oneUser.getProfileId(),7.0);

    }

    @Test
    public void jsonToMap() {

        //JSON to JAVA collection -- > de-serialization

        Gson gson = new Gson();

        String myJsonData = "{\n" +
                "    \"id\": 34,\n" +
                "    \"email\": \"sdet_blg@gmail.com\",\n" +
                "    \"name\": \"Blg\",\n" +
                "    \"company\": \"Amazon\",\n" +
                "    \"status\": \"Junior Developer\",\n" +
                "    \"profileId\": 7\n" +
                "}";

        System.out.println("myJsonData = " + myJsonData);

        System.out.println("****************************");

        //gson converting to map
        Map<String ,Object> map = gson.fromJson(myJsonData,Map.class);
        System.out.println("map = " + map);

        //gson converting to object
        EurotechUser oneUser = gson.fromJson(myJsonData,EurotechUser.class);
        System.out.println("oneUser = " + oneUser);

        //------------------------------Serialization----------------------//
        //JAVA collection or POJO to JSON

        EurotechUser eurotechUser = new EurotechUser(40,"tomhanks@gmail.com","Tom","Amazon","Tester",3);

        String jsonNewUser = gson.toJson(eurotechUser);

        System.out.println("jsonNewUser = " + jsonNewUser);


    }
}

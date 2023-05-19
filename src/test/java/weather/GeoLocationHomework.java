package weather;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GeoLocationHomework {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }
    @Test
    public void verify_Geo_Location_Is_Correct(){
        Response response = given()
                .basePath("users")
                .when()
                .get("2")                              //this method will change like post,put
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        response.prettyPrint();

        //to verify latitude is as expected
        Assert.assertEquals(response.jsonPath().getDouble("address.geo.lat"),-43.9509);

        //to verify longitude is as expected
        Assert.assertEquals(response.jsonPath().getString("address.geo.lng"),"-34.4618");

    }
    @Test
    public void verify_Geo_Location_Is_Correct_Alternate_Way(){

        ValidatableResponse validatableresponse = given()

                .basePath("users")
                .when()
                .get("2")
                .then();
        Response response = validatableresponse
                .extract()
                .response();
        Assert.assertEquals(response.getStatusCode(), 200);
        response.prettyPrint();

        validatableresponse.assertThat().body("address.geo.lat", Is.is("-43.9509"));
        validatableresponse.assertThat().body("address.geo.lng", Is.is("-34.4618"));


    }
}

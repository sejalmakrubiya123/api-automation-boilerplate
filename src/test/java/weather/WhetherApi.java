package weather;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsMapContaining.hasKey;

public class WhetherApi {


    @BeforeClass
    public void setUp(){}
    @Test
    public void Alternate_way_to_get(){

        RequestSpecification requestSpecification = given();
        requestSpecification.baseUri("https://api.openweathermap.org/data/2.5");
        requestSpecification.basePath("weather");
        requestSpecification.queryParam("lat", "44.34");
        requestSpecification.queryParam("lon", "10.99");
        requestSpecification.queryParam("appid", "326fd5972af6f7842dd86b653479e44d");

        ValidatableResponse vr = requestSpecification.when()
                .get()
                .then();

        vr.assertThat().statusCode(200);
        vr.assertThat().body("main", hasKey("feels_like"));
        vr.assertThat().body("name", Is.is("Zocca"));
    }



    @Test
    public void it_gets_weather(){
        ValidatableResponse validatableresponse =given()
                .baseUri("https://api.openweathermap.org/data/2.5")
                .basePath("weather")
                .queryParam("lat","44.34")
                .queryParam("lon","10.99")
                .queryParam("appid","326fd5972af6f7842dd86b653479e44d")
                .when()
                .get()
                .then();
        Response response = validatableresponse
                .extract()
                .response();
        Assert.assertEquals(response.getStatusCode(), 200);
        response.prettyPrint();

        validatableresponse.assertThat()
                .statusCode(200)
                .body("main", hasKey("feels_like"))
                .body("name", Is.is("Zocca"));


    }




    }

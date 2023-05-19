package posts;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BlogPostsHomework {
    @BeforeClass
    public static void setUp() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void it_should_get_post_by_id_2() {

        Response response = given()
                .basePath("todos")
                .when()
                .get("2")                              //this method will change like post,put
                .then()
                .extract().response();
        //verify response is ok
        Assert.assertEquals(response.getStatusCode(),200);

        //to verify id is 2
        Assert.assertEquals(response.jsonPath().getString("id"),"2");

        //to verify id 2 is not completed
        Assert.assertEquals(response.jsonPath().getString("completed"),"false");


        response.prettyPrint();
    }
    @Test
    public void it_should_get_post_by_id_4() {
        Response response = given()
                .basePath("todos")
                .when()
                .get("4")                              //this method will change like post,put
                .then()
                .extract().response();

        //verify response is ok
        Assert.assertEquals(response.getStatusCode(),200);

        //to verify id is 4
        Assert.assertEquals(response.jsonPath().getString("id"),"4");

        //to verify id 4 is completed
        Assert.assertEquals(response.jsonPath().getString("completed"),"true");

        response.prettyPrint();
    }

}

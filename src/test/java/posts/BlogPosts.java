package posts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BlogPosts {
    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }
    @Test
    public void it_should_get_post_by_id(){

        Response response = given()
                .basePath("posts")
                .when()
                .get("5")                              //this method will change like post,put
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("title"),"nesciunt quas odio");
        Assert.assertEquals(response.jsonPath().getString("body"),"repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque");


        //response.prettyPrint();
    }
    @Test
    public void it_should_get_comments_by_postId(){
        // https://jsonplaceholder.typicode.com/comments?postId=1
        Response response = given()
                .basePath("comments")
                .queryParam("postId","1")
                .when()
                .get()                              //this method will change like post,put
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        response.prettyPrint();
        Assert.assertEquals(response.jsonPath().getString("[0].email"),"Eliseo@gardner.biz");

    }
    @Test
    public void it_should_create_new_post(){
        Response response = given()
                .basePath("posts")
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"userId\": 12,\n" +
                        "    \"title\": \"Dummy title\",\n" +
                        "    \"body\": \"sample body\"\n" +
                        " }")
                .post()                              //this method will change like post,put
                .then()
                .extract().response();

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),201);

        Assert.assertEquals(response.jsonPath().getInt("id"),101);
        Assert.assertEquals(response.jsonPath().getInt("userId"),12);
        Assert.assertEquals(response.jsonPath().getString("title"),"Dummy title");
        Assert.assertEquals(response.jsonPath().getString("body"),"sample body");
    }

}

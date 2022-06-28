import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1 {

    //1. Could you please test if https://www.amazon.com/ is up and running without using a
    //browser? Hint: use REST API

    String url = "https://www.amazon.com/";

    @Test
    public void test1(){

        Response response = RestAssured.get(url);

        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();

        assertEquals(200, response.statusCode());
    }

    @Test
    public void test2(){

        Response response =
                given().
                        accept(ContentType.JSON)
                        .when()
                        .get(url)
                        //verify status code is 200
                        .then().statusCode(200)
                        //verify content type is text/html;charset=UTF-8
                        .and().contentType("text/html;charset=UTF-8")
                        .extract().response();


        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());

    }
}

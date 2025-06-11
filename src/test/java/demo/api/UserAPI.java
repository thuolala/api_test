package demo.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import demo.base.BaseTest;

public class UserAPI{
    public static Response getUserById(int userId) {
        return BaseTest.authSpec
                .when()
                .get("/users/" + userId);
    }

    public static Response createUser(String bodyJson) {
        return BaseTest.authSpec
                .body(bodyJson)
                .when()
                .post("/users");
    }

    public static Response putUserById(int userId, String bodyJson) {
        return BaseTest.authSpec
                .body(bodyJson)
                .log().body() // log request body
                .when()
                .put("/users/" + userId)
                .then()
                .log().body() // log response
                .extract()
                .response();
    }

    public static Response deleteUserById(int userId) {
        return BaseTest.authSpec
                .when()
                .delete("/users/" + userId)
                .then()
                .log().body() // optional: log the response
                .extract()
                .response();
    }
}


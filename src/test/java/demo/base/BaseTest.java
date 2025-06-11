package demo.base;

import demo.helper.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    public static RequestSpecification authSpec;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = PropertyReader.get("api.base.url");

        // Build login body
        String loginPayload = String.format("{\"email\":\"%s\", \"password\":\"%s\"}",
                PropertyReader.get("auth.email"),
                PropertyReader.get("auth.password"));

        // Send auth request
        Response loginResponse = RestAssured.given()
                .contentType("application/json")
                .body(loginPayload)
                .post("/login/");

        String token = loginResponse.jsonPath().getString("token");

        // Save reusable request spec
        authSpec = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json");
    }

//    public static RequestSpecification getAuthSpec() {
//        return authSpec;
//    }
}

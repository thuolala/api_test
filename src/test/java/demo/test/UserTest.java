package demo.test;

import demo.api.UserAPI;
import demo.base.BaseTest;
import demo.helper.JsonLoader;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest extends BaseTest {
    JSONObject jsonObject = new JSONObject(JsonLoader.loadJson("data.json"));

    @Test
    public void testGetUserById() {
        Response response = UserAPI.getUserById(1);

//        assertEquals(200, response.statusCode());
//        assertEquals(1, response.jsonPath().getInt("id"));

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .log().all();
    }

    @Test
    public void testCreateUser() {
        String body = jsonObject.getJSONObject("userA").toString();

        Response response = UserAPI.createUser(body);

        response.then()
                .statusCode(201)
                .body("name", equalTo("Anh Thu"))
                .body("email", equalTo("anhthu@example.com"))
                .body("password", equalTo("secure123"))
                .log().all();
    }

    @Test
    public void testUpdateUserById() {
        String updateBody = jsonObject.getJSONObject("userB").toString();

        Response response = UserAPI.putUserById(1, updateBody);

        response.then()
                .statusCode(200)
                .body("name", equalTo("Sanji"))
                .log().all();
    }

    @Test
    public void testDeleteUserById() {
        int userIdToDelete = 1;

        Response response = UserAPI.deleteUserById(userIdToDelete);

        response.then()
                .statusCode(200)
                .log().all();

        // Try to get the deleted user to confirm
//        Response getResponse = UserAPI.getUserById(userIdToDelete);
//
//        getResponse.then()
//                .statusCode(404);
    }

}